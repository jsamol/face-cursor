package pl.edu.agh.sr.facecursor.dagger.activity.main;

import android.util.DisplayMetrics;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;

import dagger.Module;
import dagger.Provides;
import pl.edu.agh.sr.facecursor.dagger.activity.ActivityModule;
import pl.edu.agh.sr.facecursor.dagger.view.camerasourceview.CameraSourceViewComponent;
import pl.edu.agh.sr.facecursor.dagger.scopes.ActivityScope;
import pl.edu.agh.sr.facecursor.ui.main.MainPresenter;
import pl.edu.agh.sr.facecursor.ui.main.MainActivity;
import pl.edu.agh.sr.facecursor.ui.main.layout.GraphicOverlay;
import pl.edu.agh.sr.facecursor.utils.PermissionUtils;
import pl.edu.agh.sr.facecursor.utils.camera.CameraSourceConfiguration;
import pl.edu.agh.sr.facecursor.utils.facetracker.FaceTrackerFactory;

@Module(subcomponents = CameraSourceViewComponent.class)
public class MainActivityModule extends ActivityModule<MainActivity> {

    public MainActivityModule(MainActivity activity) {
        super(activity);
    }

    @ActivityScope
    @Provides
    MainPresenter providePresenter(PermissionUtils permissionUtils) {
        return new MainPresenter(permissionUtils);
    }

    @ActivityScope
    @Provides
    GraphicOverlay provideGraphicOverlay() {
        return activity.getGraphicOverlay();
    }

    @ActivityScope
    @Provides
    CameraSource provideCameraSource(Detector detector, DisplayMetrics displayMetrics) {
        int portraitWidth = CameraSourceConfiguration.PREVIEW_PORTRAIT_WIDTH;
        int portraitHeight = CameraSourceConfiguration.PREVIEW_PORTRAIT_HEIGHT;

        if (displayMetrics != null) {
            portraitWidth = displayMetrics.widthPixels;
            portraitHeight = displayMetrics.heightPixels;
        }
        return new CameraSource.Builder(activity, detector)
                .setRequestedPreviewSize(portraitHeight, portraitWidth)
                .setFacing(CameraSourceConfiguration.FACING)
                .setRequestedFps(CameraSourceConfiguration.REQUESTED_FPS)
                .build();
    }

    @ActivityScope
    @Provides
    Detector provideDetector(Detector.Processor<Face> processor) {
        FaceDetector detector = new FaceDetector.Builder(activity)
                .setMode(FaceDetector.ACCURATE_MODE)
                .setLandmarkType(FaceDetector.ALL_LANDMARKS)
                .setProminentFaceOnly(true)
                .setClassificationType(FaceDetector.ALL_CLASSIFICATIONS)
                .build();

        detector.setProcessor(processor);
        return detector;
    }

    @Provides
    Detector.Processor<Face> provideProcessor(MultiProcessor.Factory<Face> factory) {
        return new MultiProcessor.Builder<>(factory).build();
    }

    @Provides
    MultiProcessor.Factory<Face> provideFactory(GraphicOverlay graphicOverlay) {
        return new FaceTrackerFactory(activity, graphicOverlay);
    }

    @Provides
    DisplayMetrics provideDisplay() {
        return activity.getResources().getDisplayMetrics();
    }
}
