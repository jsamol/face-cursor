package pl.edu.agh.sr.facecursor.ui.base;

import java.util.List;

import pl.edu.agh.sr.facecursor.utils.PermissionUtils;

public abstract class BasePresenter<T> {

    protected PermissionUtils permissionUtils;
    protected T view;

    public BasePresenter(PermissionUtils permissionUtils) {
        this.permissionUtils = permissionUtils;
    }

    public abstract void onViewCreated();
    public abstract void onViewResumed();
    public abstract void onViewPaused();
    public abstract void onViewDestroyed();
    public abstract void handlePermissionResult(int requestCode, List<String> permissions, List<Boolean> grantResults);

    public void bindView(T view) {
        this.view = view;
    }
}
