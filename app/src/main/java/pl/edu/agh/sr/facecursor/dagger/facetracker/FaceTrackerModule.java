package pl.edu.agh.sr.facecursor.dagger.facetracker;

import dagger.Module;
import dagger.Provides;
import pl.edu.agh.sr.facecursor.utils.facetracker.FaceUpdateHandler;

@Module
public class FaceTrackerModule {

    @Provides
    FaceUpdateHandler provideFaceHandler() {
        return new FaceUpdateHandler();
    }
}
