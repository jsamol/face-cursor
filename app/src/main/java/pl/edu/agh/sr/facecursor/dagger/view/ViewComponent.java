package pl.edu.agh.sr.facecursor.dagger.view;


import android.view.View;

import dagger.MembersInjector;

public interface ViewComponent<V extends View> extends MembersInjector<V> {

    interface Builder<M extends ViewModule, C extends ViewComponent> {
        Builder<M, C> viewModule(M viewModule);
        C build();
    }
}
