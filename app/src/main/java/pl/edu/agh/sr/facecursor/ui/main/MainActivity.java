package pl.edu.agh.sr.facecursor.ui.main;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.vision.CameraSource;
import com.google.common.primitives.Ints;

import java.io.IOException;
import java.util.Arrays;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import pl.edu.agh.sr.facecursor.FaceCursorApp;
import pl.edu.agh.sr.facecursor.R;
import pl.edu.agh.sr.facecursor.dagger.main.DaggerMainComponent;
import pl.edu.agh.sr.facecursor.dagger.main.MainModule;
import pl.edu.agh.sr.facecursor.presenter.main.MainPresenter;
import pl.edu.agh.sr.facecursor.ui.BaseView;
import pl.edu.agh.sr.facecursor.ui.main.layout.CameraSourceView;
import pl.edu.agh.sr.facecursor.ui.main.layout.GraphicOverlay;
import timber.log.Timber;

public class MainActivity extends BaseView<MainPresenter> implements IMainView {

    private static final int RC_HANDLE_GMS = 9001;

    @BindView(R.id.cameraSourceView)
    CameraSourceView cameraSourceView;

    @BindView(R.id.faceOverlay)
    GraphicOverlay graphicOverlay;

    @Inject
    CameraSource cameraSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initDagger();

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
        DaggerMainComponent.builder()
                .appComponent(((FaceCursorApp) getApplication()).getComponent())
                .mainModule(new MainModule())
                .build()
                .inject(this);
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
            cameraSourceView.start(graphicOverlay);
        }
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
}
