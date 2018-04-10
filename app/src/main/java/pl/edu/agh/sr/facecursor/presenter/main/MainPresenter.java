package pl.edu.agh.sr.facecursor.presenter.main;

import java.util.List;

import io.reactivex.Observable;
import pl.edu.agh.sr.facecursor.presenter.BasePresenter;
import pl.edu.agh.sr.facecursor.ui.main.MainActivity;
import pl.edu.agh.sr.facecursor.utils.AppConfiguration;
import pl.edu.agh.sr.facecursor.utils.types.PermissionResults;
import timber.log.Timber;

public class MainPresenter extends BasePresenter<MainActivity> implements IMainPresenter {

    @Override
    public void onCreate() {
        if (checkIfPermissionGranted(AppConfiguration.CAMERA_PERMISSION)) {
            view.startTracking();
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

    }

    @Override
    public void handlePermissionResult(int requestCode, List<String> permissions, List<Boolean> grantResults) {
        switch (requestCode) {
            case AppConfiguration.CAMERA_PERMISSION_REQUEST_CODE:
                Observable<String> permissionsObservable = Observable.fromIterable(permissions);
                Observable<Boolean> grantResultsObservable = Observable.fromIterable(grantResults);

                Observable.zip(permissionsObservable, grantResultsObservable, PermissionResults::new)
                        .filter(permissionResult -> permissionResult.getPermission().equals(AppConfiguration.CAMERA_PERMISSION))
                        .firstElement()
                        .subscribe(permissionResult -> {
                            if (permissionResult.getGrantResult()) {
                                view.startTracking();
                            } else {
                                requestCameraPermission();
                            }
                        }, Timber::e);

                break;
        }
    }

    private void requestCameraPermission() {
        requestPermission(AppConfiguration.CAMERA_PERMISSION_REQUEST_CODE, AppConfiguration.CAMERA_PERMISSION);
    }
}
