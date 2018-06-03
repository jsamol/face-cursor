package pl.edu.agh.sr.facecursor.dagger.keys;

import dagger.MapKey;
import pl.edu.agh.sr.facecursor.ui.base.BaseActivity;

@MapKey
public @interface ActivityKey {
    Class<? extends BaseActivity> value();
}
