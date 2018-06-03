package pl.edu.agh.sr.facecursor.dagger.utils.facetracker;

import dagger.Module;
import dagger.Provides;
import pl.edu.agh.sr.facecursor.dagger.scopes.UtilScope;
import pl.edu.agh.sr.facecursor.dagger.utils.UtilModule;
import pl.edu.agh.sr.facecursor.ui.main.layout.GraphicOverlay;
import pl.edu.agh.sr.facecursor.utils.NetworkUtils;
import pl.edu.agh.sr.facecursor.utils.facetracker.FaceGraphic;
import pl.edu.agh.sr.facecursor.utils.facetracker.FaceTracker;
import pl.edu.agh.sr.facecursor.utils.facetracker.FaceUpdateHandler;

@Module
public class FaceTrackerModule extends UtilModule<FaceTracker> {

    private GraphicOverlay mGraphicOverlay;

    public FaceTrackerModule(GraphicOverlay mGraphicOverlay) {
        this.mGraphicOverlay = mGraphicOverlay;
    }

    @UtilScope
    @Provides
    FaceUpdateHandler provideFaceUpdateHandler(NetworkUtils networkUtils, GraphicOverlay graphicOverlay, FaceGraphic faceGraphic) {
        return new FaceUpdateHandler(networkUtils, graphicOverlay, faceGraphic);
    }

    @UtilScope
    @Provides
    GraphicOverlay provideGraphicOverlay() {
        return mGraphicOverlay;
    }

    @UtilScope
    @Provides
    FaceGraphic provideFaceGraphic() {
        return new FaceGraphic(mGraphicOverlay);
    }
}
