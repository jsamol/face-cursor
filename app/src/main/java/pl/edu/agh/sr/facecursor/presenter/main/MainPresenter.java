package pl.edu.agh.sr.facecursor.presenter.main;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import pl.edu.agh.sr.facecursor.presenter.BasePresenter;
import pl.edu.agh.sr.facecursor.ui.main.MainActivity;
import pl.edu.agh.sr.facecursor.utils.PermissionUtils;
import pl.edu.agh.sr.facecursor.utils.types.PermissionResults;
import timber.log.Timber;

public class MainPresenter extends BasePresenter<MainActivity> implements IMainPresenter {

    public MainPresenter(PermissionUtils permissionUtils) {
        super(permissionUtils);
    }

    @Override
    public void onViewCreated() {
        if (permissionUtils.checkIfPermissionGranted(PermissionUtils.CAMERA_PERMISSION)) {
            if (view != null) {
                try {
                    view.startTracking();
                } catch (IOException e) {
                    Timber.e(e);
                    view.releaseCameraSource();
                }
            }
        } else {
            requestCameraPermission();
        }
    }

    @Override
    public void onViewResumed() {

    }

    @Override
    public void onViewPaused() {

    }

    @Override
    public void onViewDestroyed() {

    }

    @Override
    public void handlePermissionResult(int requestCode, List<String> permissions, List<Boolean> grantResults) {
        switch (requestCode) {
            case PermissionUtils.CAMERA_PERMISSION_REQUEST_CODE:
                Observable<String> permissionsObservable = Observable.fromIterable(permissions);
                Observable<Boolean> grantResultsObservable = Observable.fromIterable(grantResults);

                Observable.zip(permissionsObservable, grantResultsObservable, PermissionResults::new)
                        .filter(permissionResult -> permissionResult.getPermission().equals(PermissionUtils.CAMERA_PERMISSION))
                        .firstElement()
                        .subscribe(permissionResult -> {
                            if (permissionResult.getGrantResult()) {
                                if (view != null) {
                                    view.startTracking();
                                }
                            } else {
                                requestCameraPermission();
                            }
                        }, e -> {
                            if (e instanceof IOException) {
                                if (view != null) {
                                    view.releaseCameraSource();
                                }
                            }
                            Timber.e(e);
                        });

                break;
        }
    }

    @Override
    public void checkGooglePlayServicesAvailability(int availabilityCode, int successCode) {
        if (availabilityCode != successCode) {
            if (view != null) {
                view.handleGooglePlayServiceUnavailable(availabilityCode);
            }
        }
    }

    private void requestCameraPermission() {
        permissionUtils.requestPermission(view, PermissionUtils.CAMERA_PERMISSION_REQUEST_CODE, PermissionUtils.CAMERA_PERMISSION);
    }
}
