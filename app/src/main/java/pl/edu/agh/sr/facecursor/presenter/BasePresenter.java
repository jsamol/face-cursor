package pl.edu.agh.sr.facecursor.presenter;

import android.app.Activity;

import pl.edu.agh.sr.facecursor.utils.PermissionUtils;

public abstract class BasePresenter<T extends Activity> implements Presenter<T> {

    protected PermissionUtils permissionUtils;
    protected T view;

    public BasePresenter(PermissionUtils permissionUtils) {
        this.permissionUtils = permissionUtils;
    }

    @Override
    public void bindView(T view) {
        this.view = view;
    }
}
