package pl.edu.agh.sr.facecursor.ui.base;

import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import pl.edu.agh.sr.facecursor.utils.PermissionUtils;

public abstract class BaseActivity<T> extends AppCompatActivity {

    @Inject
    protected T presenter;

    @Inject
    protected PermissionUtils permissionUtils;

    public abstract void initDagger();
}
