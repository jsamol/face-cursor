package pl.edu.agh.sr.facecursor.utils.facetracker;

import com.google.android.gms.vision.face.Face;

public class FaceUpdateHandler {

    private Face mFace;

    void onFaceUpdate(Face face) {
        mFace = face;
        //TODO: Maybe some graphical features based on event?
        getFaceParameters();
    }

    private void getFaceParameters() {
        Face face = mFace;
        if (face == null) {
            return;
        }

        //TODO: Get face position and closed eyes probability and send them to the server
    }
}
