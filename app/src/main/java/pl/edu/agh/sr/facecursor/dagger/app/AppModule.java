package pl.edu.agh.sr.facecursor.dagger.app;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pl.edu.agh.sr.facecursor.ui.main.layout.SurfaceHolderCallback;
import pl.edu.agh.sr.facecursor.utils.PermissionUtils;
import pl.edu.agh.sr.facecursor.utils.camera.CameraSourceConfiguration;
import pl.edu.agh.sr.facecursor.utils.facetracker.FaceTrackerFactory;
import pl.edu.agh.sr.facecursor.utils.facetracker.FaceUpdateHandler;

@Module
public class AppModule {

    private Context mContext;

    public AppModule(Context context) {
        this.mContext = context;
    }

    @Provides
    SurfaceView provideSurfaceView() {
        return new SurfaceView(mContext);
    }

    @Provides
    SurfaceHolderCallback provideSurfaceHolderCallback() {
        return new SurfaceHolderCallback();
    }

    @Singleton
    @Provides
    CameraSource provideCameraSource(Detector detector, DisplayMetrics displayMetrics) {
        int portraitWidth = CameraSourceConfiguration.PREVIEW_PORTRAIT_WIDTH;
        int portraitHeight = CameraSourceConfiguration.PREVIEW_PORTRAIT_HEIGHT;

        if (displayMetrics != null) {
            portraitWidth = displayMetrics.widthPixels;
            portraitHeight = displayMetrics.heightPixels;
        }
        return new CameraSource.Builder(mContext, detector)
                .setRequestedPreviewSize(portraitHeight, portraitWidth)
                .setFacing(CameraSourceConfiguration.FACING)
                .setRequestedFps(CameraSourceConfiguration.REQUESTED_FPS)
                .build();
    }

    @Singleton
    @Provides
    Detector provideDetector(Detector.Processor<Face> processor) {
        FaceDetector detector = new FaceDetector.Builder(mContext)
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
    MultiProcessor.Factory<Face> provideFactory() {
        return new FaceTrackerFactory(mContext);
    }

    @Provides
    FaceUpdateHandler provideFaceUpdateHandler() {
        return new FaceUpdateHandler();
    }

    @Provides
    DisplayMetrics provideDisplay() {
        return mContext.getResources().getDisplayMetrics();
    }

    @Singleton
    @Provides
    PermissionUtils providePermissionUtils() {
        return new PermissionUtils(mContext);
    }
}
