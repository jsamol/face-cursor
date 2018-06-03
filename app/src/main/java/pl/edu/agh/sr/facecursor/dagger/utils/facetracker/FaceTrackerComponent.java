package pl.edu.agh.sr.facecursor.dagger.utils.facetracker;

import dagger.Subcomponent;
import pl.edu.agh.sr.facecursor.dagger.scopes.UtilScope;
import pl.edu.agh.sr.facecursor.dagger.utils.UtilComponent;
import pl.edu.agh.sr.facecursor.utils.facetracker.FaceTracker;

@UtilScope
@Subcomponent(modules = FaceTrackerModule.class)
public interface FaceTrackerComponent extends UtilComponent<FaceTracker> {

    FaceTracker make();

    @Subcomponent.Builder
    interface Builder extends UtilComponent.Builder<FaceTrackerModule, FaceTrackerComponent> {

    }
}
