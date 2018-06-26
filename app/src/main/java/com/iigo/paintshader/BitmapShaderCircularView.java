package com.iigo.paintshader;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * @author SamLeung
 * @Emial 729717222@qq.com
 * @date 2018/6/26 0026 14:46
 */
@SuppressLint("AppCompatCustomView")
public class BitmapShaderCircularView extends ImageView {

    private Paint paint; 
    private int radius;  //圆形头像的半径
    private float mScale; 

    public BitmapShaderCircularView(Context context) {
        super(context);
    }

    public BitmapShaderCircularView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BitmapShaderCircularView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //首先在view调用测量的时候，我们根据宽高取得一个正方形
        //并初始化半径
        int minTarget = Math.min(getMeasuredWidth(), getMeasuredHeight());
        radius = minTarget / 2;

        //重新设置宽高
        setMeasuredDimension(minTarget, minTarget);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint = new Paint();

        Bitmap bitmap = Utils.drawable2Bitmap(getDrawable());
        if (bitmap == null){
            return;
        }

        //构建BitmapShader对象，设置为CLAMP模式，当所画图形的尺寸小于bitmap尺寸的时候，会对bitmap进行裁剪
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        //我们要对bitmap进行缩放处理
        //radius * 2为宽高，再取bitmap的宽高中值小的进行相除，取得bitmap缩放比例
        mScale = (radius * 2.0f) / Math.min(bitmap.getWidth(), bitmap.getHeight());


        //设置矩阵，针对bitmap作缩放处理
        Matrix matrix = new Matrix();
        matrix.setScale(mScale, mScale);
        bitmapShader.setLocalMatrix(matrix);

        paint.setShader(bitmapShader);

        //这里开始画一个圆形，并对paint设置的BitmapShader中的bitmap进行裁剪处理
        canvas.drawCircle(radius, radius, radius, paint);
    }
}
