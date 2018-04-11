package pl.edu.agh.sr.facecursor.dagger.activity.main;

import dagger.Subcomponent;
import pl.edu.agh.sr.facecursor.dagger.activity.ActivityComponent;
import pl.edu.agh.sr.facecursor.dagger.scopes.ActivityScope;
import pl.edu.agh.sr.facecursor.dagger.view.ViewBindingModule;
import pl.edu.agh.sr.facecursor.ui.main.MainActivity;

@ActivityScope
@Subcomponent(modules = {MainActivityModule.class, ViewBindingModule.class})
public interface MainActivityComponent extends ActivityComponent<MainActivity> {

    @Subcomponent.Builder
    interface Builder extends ActivityComponent.Builder<MainActivityModule, MainActivityComponent> {

    }
}
