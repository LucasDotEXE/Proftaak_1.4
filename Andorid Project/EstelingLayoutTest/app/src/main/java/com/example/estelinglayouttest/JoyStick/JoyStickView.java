package com.example.estelinglayouttest.JoyStick;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class JoyStickView extends SurfaceView implements SurfaceHolder.Callback {

    private float centerX;
    private float centerY;
    private float baseRadius;
    private float hatRarius;

    private void setupDimensions() {

        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
        baseRadius = Math.min(getWidth(), getHeight()/3);
        hatRarius = Math.min(getWidth(), getHeight() / 5);
    }

    public JoyStickView(Context context) {
        super(context);
    }

    public JoyStickView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public JoyStickView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void drawJoystick(float newX, float newY) {
        if (getHolder().getSurface().isValid()) {
            Canvas myCanvas = this.getHolder().lockCanvas();
            Paint colors = new Paint();
            myCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            colors.setARGB(255, 50, 50, 50);
            myCanvas.drawCircle(centerX, centerY, baseRadius, colors);
            colors.setARGB(255, 0, 0, 255);
            myCanvas.drawCircle(newX, newY, hatRarius, colors);
            Log.e("f", "gud");
        } else {
            Log.e("f", "Fuck");
        }
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        setupDimensions();
        drawJoystick(centerX, centerY);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
