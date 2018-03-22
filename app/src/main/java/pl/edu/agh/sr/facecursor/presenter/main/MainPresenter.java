package pl.edu.agh.sr.facecursor.presenter.main;

import com.google.android.gms.vision.CameraSource;

import javax.inject.Inject;

import pl.edu.agh.sr.facecursor.presenter.BasePresenter;

public class MainPresenter extends BasePresenter implements IMainPresenter {

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
}
