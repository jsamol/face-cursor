package pl.edu.agh.sr.facecursor.dagger.facetracker;

import javax.inject.Singleton;

import dagger.Component;
import pl.edu.agh.sr.facecursor.utils.facetracker.FaceTracker;

@Singleton
@Component(modules = FaceTrackerModule.class)
public interface FaceTrackerComponent {
    FaceTracker make();
}
