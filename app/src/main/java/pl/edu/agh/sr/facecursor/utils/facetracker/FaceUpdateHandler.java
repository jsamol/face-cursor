package pl.edu.agh.sr.facecursor.utils.facetracker;

import com.google.android.gms.vision.face.Face;

import pl.edu.agh.sr.facecursor.ui.main.layout.GraphicOverlay;

public class FaceUpdateHandler {

    private GraphicOverlay mGraphicOverlay;
    private FaceGraphic mFaceGraphic;
    private Face mFace;

    public FaceUpdateHandler(GraphicOverlay mGraphicOverlay, FaceGraphic mFaceGraphic) {
        this.mGraphicOverlay = mGraphicOverlay;
        this.mFaceGraphic = mFaceGraphic;
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

        //TODO: Get face position and closed eyes probability and send them to the server
    }
}
