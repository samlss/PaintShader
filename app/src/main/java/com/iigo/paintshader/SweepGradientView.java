package com.iigo.paintshader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author SamLeung
 * @Emial 729717222@qq.com
 * @date 2018/6/26 0026 18:44
 */
public class SweepGradientView extends View {
    public SweepGradientView(Context context) {
        super(context);
    }

    public SweepGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SweepGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();
        int radius = 300;

        SweepGradient sweepGradient = new SweepGradient(canvasWidth / 2f,
                canvasHeight / 2f,
                new int[]{Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN},
                null);

        Paint paint = new Paint();
        paint.setShader(sweepGradient);
        canvas.drawCircle(canvasWidth / 2f, canvasHeight / 2f, radius, paint);
    }
}
