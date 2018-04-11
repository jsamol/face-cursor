package pl.edu.agh.sr.facecursor;

import android.app.Application;

import java.util.Map;

import javax.inject.Inject;

import pl.edu.agh.sr.facecursor.dagger.activity.ActivityComponent;
import pl.edu.agh.sr.facecursor.dagger.activity.ActivityModule;
import pl.edu.agh.sr.facecursor.dagger.app.AppComponent;
import pl.edu.agh.sr.facecursor.dagger.app.AppModule;
import pl.edu.agh.sr.facecursor.dagger.app.DaggerAppComponent;
import pl.edu.agh.sr.facecursor.ui.BaseActivity;
import timber.log.Timber;

public class FaceCursorApp extends Application {

    @Inject
    Map<Class<? extends BaseActivity>, ActivityComponent.Builder> activityComponentBuilders;

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        Timber.plant(new Timber.DebugTree());

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        mAppComponent.inject(this);
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public ActivityComponent.Builder<ActivityModule, ActivityComponent> getActivityComponentBuilder(Class<? extends BaseActivity> activityClass) {
        return activityComponentBuilders.containsKey(activityClass) ? activityComponentBuilders.get(activityClass) : null;
    }
}
