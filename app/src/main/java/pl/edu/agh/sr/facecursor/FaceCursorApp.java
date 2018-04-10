package pl.edu.agh.sr.facecursor;

import android.app.Application;

import pl.edu.agh.sr.facecursor.dagger.app.AppComponent;
import pl.edu.agh.sr.facecursor.dagger.app.AppModule;
import pl.edu.agh.sr.facecursor.dagger.app.DaggerAppComponent;
import timber.log.Timber;

public class FaceCursorApp extends Application {
    AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());

        component = DaggerAppComponent.builder()
                        .appModule(new AppModule(this))
                        .build();
    }

    public AppComponent getComponent() {
        return component;
    }
}
