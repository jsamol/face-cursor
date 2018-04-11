package pl.edu.agh.sr.facecursor.dagger.facetracker;

import dagger.Component;
import pl.edu.agh.sr.facecursor.utils.facetracker.FaceTracker;

@Component(modules = FaceTrackerModule.class)
public interface FaceTrackerComponent {
    FaceTracker make();
}
