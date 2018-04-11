package pl.edu.agh.sr.facecursor.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import pl.edu.agh.sr.facecursor.ui.BaseView;

public class PermissionUtils {

    public static final int CAMERA_PERMISSION_REQUEST_CODE = 1;
    public static final String CAMERA_PERMISSION = Manifest.permission.CAMERA;

    private Context mContext;

    public PermissionUtils(Context context) {
        this.mContext = context;
    }

    public boolean checkIfPermissionGranted(String permission) {
        return ActivityCompat.checkSelfPermission(mContext, permission)
                == PackageManager.PERMISSION_GRANTED;
    }

    public void requestPermission(BaseView view, int requestCode, String... permissions) {
        ActivityCompat.requestPermissions(view, permissions, requestCode);
    }
}
