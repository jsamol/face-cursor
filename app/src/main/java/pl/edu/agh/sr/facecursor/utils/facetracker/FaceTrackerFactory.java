package pl.edu.agh.sr.facecursor.utils.facetracker;

import android.content.Context;

import com.google.android.gms.vision.MultiProcessor.Factory;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.face.Face;

import pl.edu.agh.sr.facecursor.FaceCursorApp;

public class FaceTrackerFactory implements Factory<Face> {

    private Context mContext;

    public FaceTrackerFactory(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public Tracker<Face> create(Face face) {
        return ((FaceCursorApp) mContext.getApplicationContext())
                                        .getComponent()
                                        .makeFaceTracker();
    }
}
