package pl.edu.agh.sr.facecursor.presenter.main;

import java.util.List;

import pl.edu.agh.sr.facecursor.presenter.BasePresenter;
import pl.edu.agh.sr.facecursor.ui.main.MainActivity;
import pl.edu.agh.sr.facecursor.utils.AppConfiguration;

public class MainPresenter extends BasePresenter<MainActivity> implements IMainPresenter {

    @Override
    public void onCreate() {
        if (checkIfPermissionGranted(AppConfiguration.CAMERA_PERMISSION)) {
            view.startTracking();
        } else {
            requestPermission(AppConfiguration.CAMERA_PERMISSION_REQUEST_CODE, AppConfiguration.CAMERA_PERMISSION);
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

    }

    @Override
    public void handlePermissionResult(int requestCode, List<String> permissions, List<Boolean> grantResults) {
        switch (requestCode) {
            case AppConfiguration.CAMERA_PERMISSION_REQUEST_CODE:

                break;
        }
    }
}
