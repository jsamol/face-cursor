package pl.edu.agh.sr.facecursor.ui.main;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import pl.edu.agh.sr.facecursor.ui.base.BasePresenter;
import pl.edu.agh.sr.facecursor.utils.PermissionUtils;
import pl.edu.agh.sr.facecursor.utils.types.PermissionResults;
import timber.log.Timber;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    public MainPresenter(PermissionUtils permissionUtils) {
        super(permissionUtils);
    }

    @Override
    public void onViewCreated() {
        if (permissionUtils.checkIfPermissionGranted(PermissionUtils.CAMERA_PERMISSION)) {
            startTracking();
        } else {
            view.requestCameraPermissions();
        }
    }

    @Override
    public void onViewResumed() {
        startTracking();
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
                                startTracking();
                            } else {
                                view.requestCameraPermissions();
                            }
                        }, Timber::e);

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

    private void startTracking() {
        if (view != null) {
            try {
                view.startTracking();
            } catch (IOException e) {
                Timber.e(e);
            }
        }
    }
}
