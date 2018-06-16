package pl.edu.agh.sr.facecursor.utils.facetracker;

import com.google.android.gms.vision.face.Face;

import pl.edu.agh.sr.facecursor.ui.main.layout.GraphicOverlay;
import pl.edu.agh.sr.facecursor.utils.NetworkUtils;

public class FaceUpdateHandler {

    // todo need to check this value
    public static int MIN_X_VALUE = 20;
    public static int MIN_Y_VALUE = 20;
    public final static float LIMIT_CLOSE_EYE_PROBABILITY = 0.55f;
    public final static float LIMIT_OPEN_EYE_PROBABILITY = 0.80f;
    public final static long LIMIT_CLICK_MILLISEC = 500;
    public final static long REFRESH_TIME_MILLICES = 200;

    private NetworkUtils mNetworkUtils;
    private GraphicOverlay mGraphicOverlay;
    private FaceGraphic mFaceGraphic;
    private Face mFace;
    private boolean firstTime;
    private int firstX;
    private int firstY;
    private long lastClickTimestamp;
    private long lastRefreshTimestamp;

    public FaceUpdateHandler(NetworkUtils networkUtils, GraphicOverlay mGraphicOverlay, FaceGraphic mFaceGraphic) {
        this.mNetworkUtils = networkUtils;
        this.mGraphicOverlay = mGraphicOverlay;
        this.mFaceGraphic = mFaceGraphic;
        this.firstTime = true;
        this.lastClickTimestamp = System.currentTimeMillis();
        this.lastRefreshTimestamp = System.currentTimeMillis();
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
        long timestamp = System.currentTimeMillis();
        if (timestamp - lastRefreshTimestamp > REFRESH_TIME_MILLICES) {
            lastRefreshTimestamp = timestamp;
            int x = (int)(face.getPosition().x + face.getWidth() / 2);
            int y = (int)(face.getPosition().y + face.getHeight() / 2);
            int diffX = firstX - x;
            int diffY = y - firstY;
            float leftEyeOpenProbability = mFace.getIsLeftEyeOpenProbability();
            float rightEyeOpenProbability = mFace.getIsRightEyeOpenProbability();
            int click = (leftEyeOpenProbability < LIMIT_CLOSE_EYE_PROBABILITY && rightEyeOpenProbability < LIMIT_CLOSE_EYE_PROBABILITY) ? 1 : 0;

            if (timestamp - lastClickTimestamp < LIMIT_CLICK_MILLISEC) {
                click = 0;
            }

            if (click == 1) {
                lastClickTimestamp = timestamp;
            }

            if (!firstTime) {
                mNetworkUtils.send(
                        Math.abs(diffX) > MIN_X_VALUE ? diffX : 0,
                        Math.abs(diffY) > MIN_Y_VALUE ? diffY : 0,
                        click
                );
            } else {
                firstTime = false;
                firstX = x;
                firstY = y;
                mFaceGraphic.setFirstPosition(firstX, firstY);
            }
        }

    }
}
