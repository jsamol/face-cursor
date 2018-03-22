package pl.edu.agh.sr.facecursor.ui.main;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;

import butterknife.BindView;
import pl.edu.agh.sr.facecursor.R;
import pl.edu.agh.sr.facecursor.dagger.main.DaggerMainComponent;
import pl.edu.agh.sr.facecursor.dagger.main.MainModule;
import pl.edu.agh.sr.facecursor.presenter.main.MainPresenter;
import pl.edu.agh.sr.facecursor.ui.BaseView;
import pl.edu.agh.sr.facecursor.ui.main.layout.CameraSourceView;
import pl.edu.agh.sr.facecursor.utils.Configuration;

public class MainActivity extends BaseView<MainPresenter> implements IMainView {

    @BindView(R.id.cameraSourceView)
    CameraSourceView cameraSourceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDagger();

        presenter.bindView(this);
        presenter.onCreate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void initDagger() {
        DaggerMainComponent.builder()
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != Configuration.CAMERA_PERMISSION_CODE) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            return;
        }

        if (grantResults.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            presenter.startTracking();
            return;
        }

        presenter.handlePermissionDenied();
    }
}
