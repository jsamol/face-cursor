package pl.edu.agh.sr.facecursor.ui.main.layout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.ViewGroup;

import com.google.android.gms.common.images.Size;
import com.google.android.gms.vision.CameraSource;

import java.io.IOException;

import javax.inject.Inject;

import pl.edu.agh.sr.facecursor.dagger.view.camerasourceview.CameraSourceViewComponent;
import pl.edu.agh.sr.facecursor.dagger.view.camerasourceview.CameraSourceViewModule;
import pl.edu.agh.sr.facecursor.ui.main.MainActivity;
import pl.edu.agh.sr.facecursor.utils.AppConfiguration;
import pl.edu.agh.sr.facecursor.utils.PermissionUtils;
import timber.log.Timber;

public class CameraSourceView extends ViewGroup {

    private Context mContext;

    @Inject
    SurfaceView surfaceView;

    @Inject
    SurfaceHolderCallback surfaceHolderCallback;

    @Inject
    CameraSource cameraSource;

    @Inject
    GraphicOverlay graphicOverlay;

    @Inject
    PermissionUtils permissionUtils;

    private boolean isSurfaceAvailable;

    public CameraSourceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.mContext = context;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int layoutWidth = r - l;
        final int layoutHeight = b - t;

        int width = AppConfiguration.CAMERA_PREVIEW_DEFAULT_LANDSCAPE_WIDTH;
        int height = AppConfiguration.CAMERA_PREVIEW_DEFAULT_LANDSCAPE_HEIGHT;
        if (cameraSource != null) {
            Size size = cameraSource.getPreviewSize();
            if (size != null) {
                width = size.getWidth();
                height = size.getHeight();
            }
        }

        if (isPortraitMode()) {
            int swap = width;
            width = height;
            height = swap;
        }

        int childWidth = layoutWidth;
        int childHeight = (int) (((float) layoutWidth / (float) width) * height);

        if (childHeight > layoutHeight) {
            childHeight = layoutHeight;
            childWidth = (int) (((float) layoutHeight / (float) height) * width);
        }

        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).layout(0, 0, childWidth, childHeight);
        }

        try {
            start();
        } catch (IOException e) {
            Timber.e(e);
        }
    }

    public void init() {
        initDagger();

        if (surfaceView != null && surfaceHolderCallback != null) {
            surfaceHolderCallback.bindCameraSourceView(this);
            surfaceView.getHolder().addCallback(surfaceHolderCallback);
            addView(surfaceView);
        }
    }

    @SuppressLint("MissingPermission")
    public void start() throws IOException {
        if (cameraSource != null && isSurfaceAvailable && checkIfCameraPermissionGranted()) {
            cameraSource.start(surfaceView.getHolder());
            requestLayout();

            if (graphicOverlay != null) {
                Size size = cameraSource.getPreviewSize();
                int min = Math.min(size.getWidth(), size.getHeight());
                int max = Math.max(size.getWidth(), size.getHeight());
                if (isPortraitMode()) {
                    graphicOverlay.setCameraInfo(min, max, cameraSource.getCameraFacing());
                } else {
                    graphicOverlay.setCameraInfo(max, min, cameraSource.getCameraFacing());
                }
                graphicOverlay.clear();
            }
        }
    }

    public void stop() {
        if (cameraSource != null) {
            cameraSource.stop();
        }
    }

    private boolean isPortraitMode() {
        return mContext.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    void setSurfaceAvailable(boolean surfaceAvailable) {
        isSurfaceAvailable = surfaceAvailable;
    }

    private void initDagger() {
        MainActivity activity = (MainActivity) mContext;
        CameraSourceViewComponent component = (CameraSourceViewComponent) activity.getViewComponentBuilder(getClass())
                .viewModule(new CameraSourceViewModule(mContext))
                .build();

        component.injectMembers(this);
    }

    private boolean checkIfCameraPermissionGranted() {
        return permissionUtils.checkIfPermissionGranted(PermissionUtils.CAMERA_PERMISSION);
    }
}
