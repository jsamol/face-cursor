package pl.edu.agh.sr.facecursor.presenter;

import pl.edu.agh.sr.facecursor.ui.BaseView;

public interface Presenter {
    void onCreate();
    void onResume();
    void onPause();
    void onDestroy();
    void bindView(BaseView view);
}
