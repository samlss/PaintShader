package com.iigo.paintshader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author SamLeung
 * @Emial 729717222@qq.com
 * @date 2018/6/26 0026 10:53
 */
public class LGView extends View{

    private Shader.TileMode tileMode = Shader.TileMode.CLAMP; //默认

    public LGView(Context context) {
        super(context);
    }

    public LGView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LGView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();

        //x0 = 0, y0 = 0, x1 = width, y1 = 0 -> 从左到右渐变
        //x0 = 0, y0 = 0, x1 = 0, y1 = height -> 从上到下渐变
        //x0 = 0. y0 = 0, x1 = width, y1 = height -> 从左上角到右下角渐变,对角渐变

        //x = 0, y0 = 0, x1 = width / 2, y1 = 0时，只有左边的一部分进行了渲染，clamp模式下右半部分用绿色填充了，
        //而在REPEAT模式下则在水平和垂直两个方向上重复，相邻图像没有间隙，使用MIRROR模式则以镜像的方式在水平和垂直两个方向上重复，相邻图像有间隙
        LinearGradient linearGradient = new LinearGradient(0, 0, getMeasuredWidth() / 2, 0,
                new int[]{Color.RED, Color.WHITE, Color.GREEN},
                null, tileMode);

        paint.setShader(linearGradient);

        canvas.drawRect(0,0, getMeasuredWidth(), getMeasuredHeight(), paint);
    }

    public void setTileMode(Shader.TileMode tileMode){
        this.tileMode = tileMode;
        postInvalidate();
    }

}
