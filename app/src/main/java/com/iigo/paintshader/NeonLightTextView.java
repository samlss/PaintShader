package com.iigo.paintshader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * @author SamLeung
 * @Emial 729717222@qq.com
 * @date 2018/6/26 0026 10:16
 */
@SuppressLint("AppCompatCustomView")
public class NeonLightTextView extends TextView {
    private TextPaint mPaint;

    private LinearGradient mLinearGradient ;
    private Matrix mMatrix;

    private float mTranslate;
    private float DELTA_VALUE = 20; //每次减少的值

    public NeonLightTextView(Context context) {
        super(context);
    }

    public NeonLightTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NeonLightTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mPaint = getPaint(); //获取当前TextView的画笔

        String text = getText().toString();

        float textWith = mPaint.measureText(getText().toString());

        // 6个文字的宽度
        int gradientSize = (int) (textWith / text.length() * 6);

        //draw的时候会从给定的颜色数组里面取值，这里字的颜色为黑色，然后通过alpha透明值的不同来实现霓虹灯效果
        int[] colors = new int[]{
                0x22000000, 0xff000000, 0x22000000};

        //每次渲染的区域为 gradientSize,且横向渲染
        //我们设置初始从-gradientSize开始
        mLinearGradient = new LinearGradient(-gradientSize,
                0,
                0,
                0,
                colors,
                null,
                Shader.TileMode.CLAMP);

        mPaint.setShader(mLinearGradient);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float textWidth = getPaint().measureText(getText().toString()); //首先获取要绘制的文字的宽度

        if(mTranslate >= textWidth){
            mTranslate = 0;
        }else{
            mTranslate += DELTA_VALUE;
        }

        mMatrix = new Matrix();
        mMatrix.setTranslate(mTranslate, 0);
        mLinearGradient.setLocalMatrix(mMatrix);

        postInvalidateDelayed(50); //实现动画效果
    }
}
