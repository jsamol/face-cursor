package pl.edu.agh.sr.facecursor.dagger.activity;

import dagger.MembersInjector;
import pl.edu.agh.sr.facecursor.ui.base.BaseActivity;

public interface ActivityComponent<A extends BaseActivity> extends MembersInjector<A> {

    interface Builder<M extends ActivityModule, C extends ActivityComponent> {
        Builder<M, C> activityModule(M activityModule);
        C build();
    }
}
