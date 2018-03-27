package pl.edu.agh.sr.facecursor.dagger.main;

import com.google.android.gms.vision.CameraSource;

import dagger.Module;
import dagger.Provides;
import pl.edu.agh.sr.facecursor.presenter.main.MainPresenter;

@Module
public class MainModule {

    @Provides
    MainPresenter providePresenter(CameraSource cameraSource) {
        return new MainPresenter(cameraSource);
    }
}
