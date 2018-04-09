package pl.edu.agh.sr.facecursor.presenter;

import android.app.Activity;

public interface Presenter<T extends Activity> {
    void onCreate();
    void onResume();
    void onPause();
    void onDestroy();
    void bindView(T view);
}
