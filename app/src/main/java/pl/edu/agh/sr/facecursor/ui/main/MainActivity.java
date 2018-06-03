package pl.edu.agh.sr.facecursor.ui.main;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.vision.CameraSource;
import com.google.common.primitives.Ints;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import pl.edu.agh.sr.facecursor.FaceCursorApp;
import pl.edu.agh.sr.facecursor.R;
import pl.edu.agh.sr.facecursor.dagger.activity.main.MainActivityComponent;
import pl.edu.agh.sr.facecursor.dagger.activity.main.MainActivityModule;
import pl.edu.agh.sr.facecursor.dagger.view.ViewComponent;
import pl.edu.agh.sr.facecursor.dagger.view.ViewModule;
import pl.edu.agh.sr.facecursor.ui.base.BaseActivity;
import pl.edu.agh.sr.facecursor.ui.main.layout.CameraSourceView;
import pl.edu.agh.sr.facecursor.ui.main.layout.GraphicOverlay;
import pl.edu.agh.sr.facecursor.utils.PermissionUtils;
import timber.log.Timber;

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    private static final int RC_HANDLE_GMS = 9001;

    @BindView(R.id.cameraSourceView)
    CameraSourceView cameraSourceView;

    @BindView(R.id.faceOverlay)
    GraphicOverlay graphicOverlay;

    @Inject
    CameraSource cameraSource;

    @Inject
    Map<Class<? extends View>, ViewComponent.Builder> viewComponentBuilders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initDagger();
        cameraSourceView.init();

        presenter.bindView(this);
        presenter.onViewCreated();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onViewResumed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        cameraSourceView.stop();
        presenter.onViewPaused();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseCameraSource();
        presenter.onViewDestroyed();
    }

    @Override
    public void initDagger() {
        FaceCursorApp app = (FaceCursorApp) getApplicationContext();
        MainActivityComponent component = (MainActivityComponent) app.getActivityComponentBuilder(this.getClass())
                .activityModule(new MainActivityModule(this))
                .build();

        component.injectMembers(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Observable.fromIterable(Ints.asList(grantResults))
                .map(grantResult -> grantResult == PackageManager.PERMISSION_GRANTED)
                .toList()
                .subscribe(results -> presenter.handlePermissionResult(requestCode, Arrays.asList(permissions), results), Timber::e);
    }

    @Override
    public void startTracking() throws IOException {
        presenter.checkGooglePlayServicesAvailability(GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getApplicationContext()),
                                                      ConnectionResult.SUCCESS);

        if (cameraSource != null) {
            cameraSourceView.start();
        }
    }

    @Override
    public void requestCameraPermissions() {
        permissionUtils.requestPermission(this, PermissionUtils.CAMERA_PERMISSION_REQUEST_CODE, PermissionUtils.CAMERA_PERMISSION);
    }

    @Override
    public void releaseCameraSource() {
        if (cameraSource != null) {
            cameraSource.release();
            cameraSource = null;
        }
    }

    @Override
    public void handleGooglePlayServiceUnavailable(int availabilityCode) {
        GoogleApiAvailability.getInstance().getErrorDialog(this, availabilityCode, RC_HANDLE_GMS).show();
    }

    public GraphicOverlay getGraphicOverlay() {
        return graphicOverlay;
    }

    public ViewComponent.Builder<ViewModule, ViewComponent> getViewComponentBuilder(Class<? extends View> viewClass) {
        return viewComponentBuilders.containsKey(viewClass) ? viewComponentBuilders.get(viewClass) : null;
    }
}
