package pl.edu.agh.sr.facecursor.dagger.utils;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import pl.edu.agh.sr.facecursor.dagger.keys.UtilKey;
import pl.edu.agh.sr.facecursor.dagger.utils.facetracker.FaceTrackerComponent;
import pl.edu.agh.sr.facecursor.utils.facetracker.FaceTracker;

@Module(subcomponents = FaceTrackerComponent.class)
public abstract class UtilBindingModule {

    @Binds
    @IntoMap
    @UtilKey(FaceTracker.class)
    public abstract UtilComponent.Builder faceTrackerBuilder(FaceTrackerComponent.Builder builder);
}
