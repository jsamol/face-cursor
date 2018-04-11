package pl.edu.agh.sr.facecursor.dagger.facetracker;

import dagger.Module;
import dagger.Provides;
import pl.edu.agh.sr.facecursor.ui.main.layout.GraphicOverlay;
import pl.edu.agh.sr.facecursor.utils.facetracker.FaceGraphic;
import pl.edu.agh.sr.facecursor.utils.facetracker.FaceUpdateHandler;

@Module
public class FaceTrackerModule {

    private GraphicOverlay mGraphicOverlay;

    public FaceTrackerModule(GraphicOverlay mGraphicOverlay) {
        this.mGraphicOverlay = mGraphicOverlay;
    }

    @Provides
    FaceUpdateHandler provideFaceUpdateHandler(GraphicOverlay graphicOverlay, FaceGraphic faceGraphic) {
        return new FaceUpdateHandler(graphicOverlay, faceGraphic);
    }

    @Provides
    GraphicOverlay provideGraphicOverlay() {
        return mGraphicOverlay;
    }

    @Provides
    FaceGraphic provideFaceGraphic() {
        return new FaceGraphic(mGraphicOverlay);
    }
}
