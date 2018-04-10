package pl.edu.agh.sr.facecursor.ui.main.layout;

import android.view.SurfaceHolder;

import java.io.IOException;

import timber.log.Timber;

public class SurfaceHolderCallback implements SurfaceHolder.Callback {

    private CameraSourceView mCameraSourceView;

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mCameraSourceView.setSurfaceAvailable(true);
        try {
            mCameraSourceView.start();
        } catch (IOException e) {
            Timber.e(e);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mCameraSourceView.setSurfaceAvailable(false);
    }

    void bindCameraSourceView(CameraSourceView cameraSourceView) {
        this.mCameraSourceView = cameraSourceView;
    }
}
