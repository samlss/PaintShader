package com.iigo.paintshader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author SamLeung
 * @Emial 729717222@qq.com
 * @date 2018/6/26 0026 18:18
 */
public class RadialGradientView extends View {
    public RadialGradientView(Context context) {
        super(context);
    }

    public RadialGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RadialGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //创建一个圆心为(300, 300)半径为100的渐变圆
        RadialGradient radialGradient = new RadialGradient(300,
                300,
                100,
                new int[]{Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW},
                null, Shader.TileMode.MIRROR);

        //只有两个渐变颜色，分别为圆心其实颜色 -> 边缘颜色
//        RadialGradient radialGradient = new RadialGradient(300,
//                300,
//                100,
//                Color.RED,
//                Color.WHITE, Shader.TileMode.CLAMP);

        Paint paint = new Paint();
        paint.setShader(radialGradient);
        canvas.drawCircle(300, 300, 300, paint);
    }
}
