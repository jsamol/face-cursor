package pl.edu.agh.sr.facecursor.dagger.activity;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import pl.edu.agh.sr.facecursor.dagger.activity.main.MainActivityComponent;
import pl.edu.agh.sr.facecursor.dagger.keys.ActivityKey;
import pl.edu.agh.sr.facecursor.ui.main.MainActivity;

@Module(subcomponents = MainActivityComponent.class)
public abstract class ActivityBindingModule {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    public abstract ActivityComponent.Builder mainComponentBuilder(MainActivityComponent.Builder builder);
}
