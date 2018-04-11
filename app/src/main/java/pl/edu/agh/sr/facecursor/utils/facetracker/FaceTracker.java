package pl.edu.agh.sr.facecursor.utils.facetracker;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.face.Face;

import javax.inject.Inject;

public class FaceTracker extends Tracker<Face> {

    private FaceUpdateHandler mFaceUpdateHandler;

    @Inject
    public FaceTracker(FaceUpdateHandler mFaceUpdateHandler) {
        this.mFaceUpdateHandler = mFaceUpdateHandler;
    }

    @Override
    public void onNewItem(int i, Face face) {
        mFaceUpdateHandler.onNewFace(i);
    }

    @Override
    public void onUpdate(Detector.Detections<Face> detections, Face face) {
        mFaceUpdateHandler.onFaceUpdate(face);
    }

    @Override
    public void onMissing(Detector.Detections<Face> detections) {
        mFaceUpdateHandler.onFaceMissing();
    }

    @Override
    public void onDone() {
        mFaceUpdateHandler.onFaceMissing();
    }
}
