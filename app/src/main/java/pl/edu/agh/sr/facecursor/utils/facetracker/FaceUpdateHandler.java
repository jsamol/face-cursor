package pl.edu.agh.sr.facecursor.utils.facetracker;

import com.google.android.gms.vision.face.Face;

import pl.edu.agh.sr.facecursor.ui.main.layout.GraphicOverlay;
import pl.edu.agh.sr.facecursor.utils.NetworkUtils;
import timber.log.Timber;

public class FaceUpdateHandler {

    // todo need to check this value
    public static int LIMIT_X_VALUE = 10;
    public static int LIMIT_Y_VALUE = 10;
    public final static float LIMIT_CLOSE_EYE_PROBABILITY = 0.55f;
    public final static float LIMIT_OPEN_EYE_PROBABILITY = 0.80f;
    public final static long LIMIT_CLICK_MILISEC = 500;

    private NetworkUtils mNetworkUtils;
    private GraphicOverlay mGraphicOverlay;
    private FaceGraphic mFaceGraphic;
    private Face mFace;
    private boolean firstTime;
    private int previousX;
    private int previousY;
    public long lastClickTimestamp;

    public FaceUpdateHandler(NetworkUtils networkUtils, GraphicOverlay mGraphicOverlay, FaceGraphic mFaceGraphic) {
        this.mNetworkUtils = networkUtils;
        this.mGraphicOverlay = mGraphicOverlay;
        this.mFaceGraphic = mFaceGraphic;
        this.firstTime = true;
        this.lastClickTimestamp = System.currentTimeMillis();
    }

    void onNewFace(int faceId) {
        mFaceGraphic.setId(faceId);
    }

    void onFaceUpdate(Face face) {
        mFace = face;
        mGraphicOverlay.addGraphic(mFaceGraphic);
        mFaceGraphic.updateFace(face);
        getFaceParameters();
    }

    void onFaceMissing() {
        mGraphicOverlay.removeGraphic(mFaceGraphic);
    }

    private void getFaceParameters() {
        Face face = mFace;
        if (face == null) {
            return;
        }

        int x = (int) mFace.getPosition().x;
        int y = (int) mFace.getPosition().y;
        int diffX = previousX - x;
        int diffY = previousX - y;
        float leftEyeOpenProbability = mFace.getIsLeftEyeOpenProbability();
        float rightEyeOpenProbability = mFace.getIsRightEyeOpenProbability();
        int click = (leftEyeOpenProbability < LIMIT_CLOSE_EYE_PROBABILITY && rightEyeOpenProbability < LIMIT_CLOSE_EYE_PROBABILITY) ? 1 : 0;
        long timestamp = System.currentTimeMillis();
//        Timber.d("Timestamp diff = %s", (timestamp - lastClickTimestamp));
        if (timestamp - lastClickTimestamp < LIMIT_CLICK_MILISEC) {
            click = 0;
        }

        if (click == 1) {
            lastClickTimestamp = timestamp;
        }

        if (!firstTime) {
            mNetworkUtils.send(
                    Math.abs(diffX) > LIMIT_X_VALUE ? diffX : 0,
                    Math.abs(diffY) > LIMIT_Y_VALUE ? diffY : 0,
                    click
            );
        } else {
            firstTime = false;
        }


        previousX = x;
        previousY = y;
    }
}
