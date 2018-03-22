package pl.edu.agh.sr.facecursor.presenter.main;

import com.google.android.gms.vision.CameraSource;

import pl.edu.agh.sr.facecursor.presenter.BasePresenter;
import pl.edu.agh.sr.facecursor.ui.main.MainActivity;

public class MainPresenter extends BasePresenter<MainActivity> implements IMainPresenter {

    private CameraSource mCameraSource;

    public MainPresenter(CameraSource cameraSource) {
        this.mCameraSource = cameraSource;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        if (mCameraSource != null) {
            mCameraSource.release();
        }
    }

    @Override
    public void startTracking() {

    }

    @Override
    public void handlePermissionDenied() {

    }

}
