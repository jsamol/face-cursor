package pl.edu.agh.sr.facecursor.dagger.activity;

import dagger.Module;
import dagger.Provides;
import pl.edu.agh.sr.facecursor.dagger.scopes.ActivityScope;

@Module
public abstract class ActivityModule<T> {
    protected final T activity;

    public ActivityModule(T activity) {
        this.activity = activity;
    }

    @ActivityScope
    @Provides
    T provideActivity() {
        return activity;
    }
}
