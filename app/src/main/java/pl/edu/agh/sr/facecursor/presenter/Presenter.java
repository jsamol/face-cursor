package pl.edu.agh.sr.facecursor.presenter;

import android.app.Activity;

import java.util.List;

public interface Presenter<T extends Activity> {
    void onCreate();
    void onResume();
    void onPause();
    void onDestroy();
    void bindView(T view);
    void handlePermissionResult(int requestCode, List<String> permissions, List<Boolean> grantResults);
}
