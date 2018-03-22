package pl.edu.agh.sr.facecursor.dagger.main;

import javax.inject.Singleton;

import dagger.Component;
import pl.edu.agh.sr.facecursor.ui.main.MainActivity;

@Singleton
@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity activity);
}
