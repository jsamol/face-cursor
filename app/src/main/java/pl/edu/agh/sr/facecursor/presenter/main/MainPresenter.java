package pl.edu.agh.sr.facecursor.presenter.main;

import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import com.google.android.gms.vision.CameraSource;

import pl.edu.agh.sr.facecursor.presenter.BasePresenter;
import pl.edu.agh.sr.facecursor.ui.main.MainActivity;
import pl.edu.agh.sr.facecursor.utils.AppConfiguration;

public class MainPresenter extends BasePresenter<MainActivity> implements IMainPresenter {

    private CameraSource mCameraSource;

    public MainPresenter(CameraSource cameraSource) {
        this.mCameraSource = cameraSource;
    }

    @Override
    public void onCreate() {
        if (checkIfCameraPermissionGranted()) {
            startTracking();
        } else {
            requestCameraPermission();
        }
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

    private boolean checkIfCameraPermissionGranted() {
        return ActivityCompat.checkSelfPermission(view.getApplicationContext(), AppConfiguration.CAMERA_PERMISSION)
                == PackageManager.PERMISSION_GRANTED;
    }

    private void requestCameraPermission() {
        ActivityCompat.requestPermissions(view, new String[] { AppConfiguration.CAMERA_PERMISSION }, AppConfiguration.CAMERA_PERMISSION_CODE);
    }

}
