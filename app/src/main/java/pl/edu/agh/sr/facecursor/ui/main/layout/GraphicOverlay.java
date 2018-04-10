package pl.edu.agh.sr.facecursor.ui.main.layout;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.google.android.gms.vision.CameraSource;

import java.util.HashSet;
import java.util.Set;

import pl.edu.agh.sr.facecursor.utils.camera.CameraSourceConfiguration;

public class GraphicOverlay extends View {
    private final Object mLock;

    private int mPreviewWidth;
    private float mWidthScaleFactor;

    private int mPreviewHeight;
    private float mHeightScaleFactor;

    private int mFacing;
    private Set<Graphic> mGraphics;

    public GraphicOverlay(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mLock = new Object();

        mWidthScaleFactor = 1.0f;
        mHeightScaleFactor = 1.0f;
        mFacing = CameraSourceConfiguration.FACING;
        mGraphics = new HashSet<>();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        synchronized (mLock) {
            if (mPreviewWidth != 0 && mPreviewHeight != 0) {
                mWidthScaleFactor = (float) canvas.getWidth() / (float) mPreviewWidth;
                mHeightScaleFactor = (float) canvas.getHeight() / (float) mPreviewHeight;
            }

            for (Graphic graphic : mGraphics) {
                graphic.draw(canvas);
            }
        }
    }

    public void setCameraInfo(int previewWidth, int previewHeight, int facing) {
        synchronized (mLock) {
            mPreviewHeight = previewHeight;
            mPreviewWidth = previewWidth;
            mFacing = facing;
        }
        postInvalidate();
    }

    public void clear() {
        synchronized (mLock) {
            mGraphics.clear();
        }
        postInvalidate();
    }

    public void addGraphic(Graphic graphic) {
        synchronized (mLock) {
            mGraphics.add(graphic);
        }
        postInvalidate();
    }

    public void removeGraphic(Graphic graphic) {
        synchronized (mLock) {
            mGraphics.remove(graphic);
        }
        postInvalidate();
    }

    public static abstract class Graphic {
        private GraphicOverlay mGraphicOverlay;

        public Graphic(GraphicOverlay mGraphicOverlay) {
            this.mGraphicOverlay = mGraphicOverlay;
        }

        public abstract void draw(Canvas canvas);

        public float scaleX(float horizontal) {
            return horizontal * mGraphicOverlay.mWidthScaleFactor;
        }

        public float scaleY(float vertical) {
            return vertical * mGraphicOverlay.mHeightScaleFactor;
        }

        public float translateX(float x) {
            if (mGraphicOverlay.mFacing == CameraSource.CAMERA_FACING_FRONT) {
                return mGraphicOverlay.getWidth() - scaleX(x);
            }
            return scaleX(x);
        }

        public float translateY(float y) {
            return scaleY(y);
        }

        public void postInvalidate() {
            mGraphicOverlay.postInvalidate();
        }
    }
}
