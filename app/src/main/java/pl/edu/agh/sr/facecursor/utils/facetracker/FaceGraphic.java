package pl.edu.agh.sr.facecursor.utils.facetracker;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.google.android.gms.vision.face.Face;

import java.util.Locale;

import pl.edu.agh.sr.facecursor.ui.main.layout.GraphicOverlay;
import timber.log.Timber;

public class FaceGraphic extends GraphicOverlay.Graphic {
    private static final float FACE_POSITION_RADIUS = 10.0f;
    private static final float ID_TEXT_SIZE = 40.0f;
    private static final float ID_X_OFFSET = 50.0f;
    private static final float ID_Y_OFFSET = -50.0f;
    private static final float BOX_STROKE_WIDTH = 5.0f;

    private static final int COLOR_CHOICES[] = {
            Color.BLUE,
            Color.CYAN,
            Color.GREEN,
            Color.MAGENTA,
            Color.RED,
            Color.WHITE,
            Color.YELLOW
    };

    private static int mCurrentColorIndex = -1;

    private Paint mFacePositionPaint;
    private Paint mIdPaint;
    private Paint mBoxPaint;

    private volatile Face mFace;
    private int mFaceId;
    private float mFaceHappiness;

    private float firstX;
    private float firstY;

    public FaceGraphic(GraphicOverlay mGraphicOverlay) {
        super(mGraphicOverlay);

        mCurrentColorIndex = (mCurrentColorIndex + 1) % COLOR_CHOICES.length;
        final int selectedColor = COLOR_CHOICES[mCurrentColorIndex];

        mFacePositionPaint = new Paint();
        mFacePositionPaint.setColor(selectedColor);

        mIdPaint = new Paint();
        mIdPaint.setColor(selectedColor);
        mIdPaint.setTextSize(ID_TEXT_SIZE);

        mBoxPaint = new Paint();
        mBoxPaint.setColor(selectedColor);
        mBoxPaint.setStyle(Paint.Style.STROKE);
        mBoxPaint.setStrokeWidth(BOX_STROKE_WIDTH);
    }

    @Override
    public void draw(Canvas canvas) {
        Face face = mFace;
        if (face == null) {
            return;
        }

        float x = translateX(face.getPosition().x + face.getWidth() / 2);
        float y = translateY(face.getPosition().y + face.getHeight() / 2);
        canvas.drawCircle(x, y, FACE_POSITION_RADIUS, mFacePositionPaint);

        String idText = String.format(Locale.getDefault(), "id: %d", mFaceId);
        String happinessText = String.format(Locale.getDefault(), "happiness: %.2f", face.getIsSmilingProbability());
        String rightEyeText = String.format(Locale.getDefault(), "right eye: %.2f", face.getIsRightEyeOpenProbability());
        String leftEyeText = String.format(Locale.getDefault(), "left eye: %.2f", face.getIsLeftEyeOpenProbability());

        float xOffset = scaleX(face.getWidth() / 2.0f);
        float yOffset = scaleY(face.getHeight() / 2.0f);
        float left = x - xOffset;
        float top = y - yOffset;
        float right = x + xOffset;
        float bottom = y + yOffset;

        canvas.drawText(idText, left + ID_X_OFFSET, bottom + 3 * ID_Y_OFFSET, mIdPaint);
//        Timber.d(idText);

//        Timber.d(happinessText);

        canvas.drawText(rightEyeText, left + ID_X_OFFSET, bottom + 2 * ID_Y_OFFSET, mIdPaint);
//        Timber.d(rightEyeText);

        canvas.drawText(leftEyeText, left + ID_X_OFFSET, bottom + ID_Y_OFFSET, mIdPaint);
//        Timber.d(leftEyeText);

        canvas.drawRect(left, top, right, bottom, mBoxPaint);

        canvas.drawLine(firstX, 0, firstX, mGraphicOverlay.getHeight(), mIdPaint);
        canvas.drawLine(0, firstY, mGraphicOverlay.getWidth(), firstY, mIdPaint);
    }

    void setId(int id) {
        mFaceId = id;
    }

    void updateFace(Face face) {
        mFace = face;
        postInvalidate();
    }

    public void setFirstPosition(int firstX, int firstY) {
        this.firstX = translateX(firstX);
        this.firstY = translateY(firstY);
    }
}
