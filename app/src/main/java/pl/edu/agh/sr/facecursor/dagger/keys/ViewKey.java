package pl.edu.agh.sr.facecursor.dagger.keys;

import android.view.View;

import dagger.MapKey;

@MapKey
public @interface ViewKey {
    Class<? extends View> value();
}
