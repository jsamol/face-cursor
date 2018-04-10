package pl.edu.agh.sr.facecursor.presenter;

import android.app.Activity;

import java.util.List;

public interface Presenter<T extends Activity> {
    void onViewCreated();
    void onViewResumed();
    void onViewPaused();
    void onViewDestroyed();
    void bindView(T view);
    void handlePermissionResult(int requestCode, List<String> permissions, List<Boolean> grantResults);
}
