package pl.edu.agh.sr.facecursor.dagger.app;

import android.view.View;

import com.google.android.gms.vision.CameraSource;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import pl.edu.agh.sr.facecursor.FaceCursorApp;
import pl.edu.agh.sr.facecursor.ui.main.layout.CameraSourceView;
import pl.edu.agh.sr.facecursor.utils.facetracker.FaceTracker;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent extends AndroidInjector<FaceCursorApp> {
    void injectCameraSourceView(CameraSourceView cameraSourceView);

    CameraSource makeCameraSource();

    FaceTracker makeFaceTracker();
}
