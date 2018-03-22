package pl.edu.agh.sr.facecursor.utils.facetracker;

import com.google.android.gms.vision.MultiProcessor.Factory;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.face.Face;

public class FaceTrackerFactory implements Factory<Face> {
    @Override
    public Tracker<Face> create(Face face) {
        return new FaceTracker();
    }
}
