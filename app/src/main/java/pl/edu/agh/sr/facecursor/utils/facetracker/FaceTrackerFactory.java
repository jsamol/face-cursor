package pl.edu.agh.sr.facecursor.utils.facetracker;

import com.google.android.gms.vision.MultiProcessor.Factory;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.face.Face;

import pl.edu.agh.sr.facecursor.dagger.facetracker.DaggerFaceTrackerComponent;
import pl.edu.agh.sr.facecursor.dagger.facetracker.FaceTrackerModule;
import pl.edu.agh.sr.facecursor.ui.main.layout.GraphicOverlay;

public class FaceTrackerFactory implements Factory<Face> {

    private GraphicOverlay mGraphicOverlay;

    public FaceTrackerFactory(GraphicOverlay mGraphicOverlay) {
        this.mGraphicOverlay = mGraphicOverlay;
    }

    @Override
    public Tracker<Face> create(Face face) {
        return DaggerFaceTrackerComponent.builder()
                .faceTrackerModule(new FaceTrackerModule(mGraphicOverlay))
                .build()
                .make();
    }
}
