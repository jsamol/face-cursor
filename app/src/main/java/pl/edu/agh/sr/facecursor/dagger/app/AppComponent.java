package pl.edu.agh.sr.facecursor.dagger.app;

import javax.inject.Singleton;

import dagger.Component;
import pl.edu.agh.sr.facecursor.FaceCursorApp;
import pl.edu.agh.sr.facecursor.dagger.activity.ActivityBindingModule;
import pl.edu.agh.sr.facecursor.dagger.utils.UtilBindingModule;
import pl.edu.agh.sr.facecursor.utils.PermissionUtils;

@Singleton
@Component(modules = {AppModule.class,
                      ActivityBindingModule.class,
                      UtilBindingModule.class})
public interface AppComponent {
    FaceCursorApp inject(FaceCursorApp faceCursorApp);
}
