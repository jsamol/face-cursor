package pl.edu.agh.sr.facecursor.dagger.main;

import dagger.Module;
import dagger.Provides;
import pl.edu.agh.sr.facecursor.presenter.main.MainPresenter;
import pl.edu.agh.sr.facecursor.utils.PermissionUtils;

@Module
public class MainModule {

    @Provides
    MainPresenter providePresenter(PermissionUtils permissionUtils) {
        return new MainPresenter(permissionUtils);
    }
}
