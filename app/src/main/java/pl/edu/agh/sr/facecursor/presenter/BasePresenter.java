package pl.edu.agh.sr.facecursor.presenter;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

public abstract class BasePresenter<T extends Activity> implements Presenter<T> {
    protected T view;

    @Override
    public void bindView(T view) {
        this.view = view;
    }

    protected boolean checkIfPermissionGranted(String permission) {
        return ActivityCompat.checkSelfPermission(view.getApplicationContext(), permission)
                == PackageManager.PERMISSION_GRANTED;
    }

    protected void requestPermission(int requestCode, String... permissions) {
        ActivityCompat.requestPermissions(view, permissions, requestCode);
    }
}
