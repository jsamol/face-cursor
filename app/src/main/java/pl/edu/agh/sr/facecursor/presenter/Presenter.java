package pl.edu.agh.sr.facecursor.presenter;

public interface Presenter<T> {
    void onCreate();
    void onResume();
    void onPause();
    void onDestroy();
    void bindView(T view);
}
