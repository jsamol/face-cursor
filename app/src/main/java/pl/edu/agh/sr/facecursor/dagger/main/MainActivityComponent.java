package pl.edu.agh.sr.facecursor.dagger.main;

import dagger.Component;
import pl.edu.agh.sr.facecursor.dagger.app.AppComponent;
import pl.edu.agh.sr.facecursor.dagger.scopes.ActivityScope;
import pl.edu.agh.sr.facecursor.ui.main.MainActivity;

@ActivityScope
@Component(dependencies = AppComponent.class,
        modules = MainActivityModule.class)
public interface MainActivityComponent {
    void inject(MainActivity activity);
}
