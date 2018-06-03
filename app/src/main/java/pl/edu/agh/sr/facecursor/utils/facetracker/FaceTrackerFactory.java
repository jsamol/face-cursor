package pl.edu.agh.sr.facecursor.utils.facetracker;

import android.content.Context;

import com.google.android.gms.vision.MultiProcessor.Factory;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.face.Face;

import pl.edu.agh.sr.facecursor.FaceCursorApp;
import pl.edu.agh.sr.facecursor.dagger.utils.facetracker.FaceTrackerComponent;
import pl.edu.agh.sr.facecursor.dagger.utils.facetracker.FaceTrackerModule;
import pl.edu.agh.sr.facecursor.ui.main.layout.GraphicOverlay;

public class FaceTrackerFactory implements Factory<Face> {

    private Context mContext;
    private GraphicOverlay mGraphicOverlay;

    public FaceTrackerFactory(Context context, GraphicOverlay mGraphicOverlay) {
        this.mContext = context;
        this.mGraphicOverlay = mGraphicOverlay;
    }

    @Override
    public Tracker<Face> create(Face face) {
        FaceCursorApp app = (FaceCursorApp) mContext.getApplicationContext();
        FaceTrackerComponent component = (FaceTrackerComponent) app.getUtilComponentBuilder(FaceTracker.class)
                .utilModule(new FaceTrackerModule(mGraphicOverlay))
                .build();
        return component.make();
    }
}
