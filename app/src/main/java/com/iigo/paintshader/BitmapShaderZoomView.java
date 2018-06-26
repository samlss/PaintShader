package com.iigo.paintshader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author SamLeung
 * @Emial 729717222@qq.com
 * @date 2018/6/26 0026 15:15
 */
public class BitmapShaderZoomView extends View {
    private static final int ENLARGE_FACTOR = 2; //放大因素，即放大镜的放大倍数
    private static final int RADIUS  = 100; //放大镜的半径

    private Bitmap bitmap; //原图
    private Bitmap enlargeBitmap; //放大 2 倍后的bitmap对象
    private ShapeDrawable shapeDrawable; //放大镜内容

    private Matrix mMatrix; //矩阵

    public BitmapShaderZoomView(Context context) {
        super(context);
    }

    public BitmapShaderZoomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BitmapShaderZoomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        initBitmap(w, h);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //我们首先绘制原bitmap图
        canvas.drawBitmap(bitmap, 0 , 0 , null);

        //这里绘制放大镜的内容
        //原理是通过BitmapShader的位图处理，通过制定的CLAMP模式，当所画图形的尺寸大于Bitmap的尺寸的时候，会用Bitmap四边的颜色填充剩余空间，小于的时候则会对Bitmap进行裁剪
        //这里会对放大后的bitmap进行裁剪，裁剪的大小和形状由shapeDrawable决定
        shapeDrawable.draw(canvas);
    }

    /**
     * 初始化bitmap，BitmapShader，ShapeDrawable等对象
     *
     * @param width 当前view的宽
     * @param height 当前view的高
     * */
    private void initBitmap(int width, int height){
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.head);
        bitmap = Bitmap.createScaledBitmap(bitmap, width, height,true); //首先将原图铺满屏幕

        //我们这里将原图放大两倍
        enlargeBitmap = Bitmap.createScaledBitmap(bitmap,bitmap.getWidth() * ENLARGE_FACTOR,
                bitmap.getHeight() * ENLARGE_FACTOR,true);

        //这里设置BitmapShader的bitmap为放大后的bitmap
        BitmapShader bitmapShader = new BitmapShader(enlargeBitmap, Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP);

        //再创建一个ShapeDrawable图形对象
        shapeDrawable = new ShapeDrawable(new OvalShape());
        shapeDrawable.getPaint().setShader(bitmapShader);

        //设置初始放大镜位置和大小
        shapeDrawable.setBounds(0,0,RADIUS * 2,RADIUS * 2);

        mMatrix = new Matrix();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();


        //将放大的图片往相反的方向移动
        //这里的目的是通过移动放大的图，然后取得放大镜的内容，之所以是RADIUS - x * ENLARGE_FACTOR和RADIUS - y * ENLARGE_FACTOR)
        //是因为设置的放大镜的初始坐标为(RADIUS,RADIUS)
        mMatrix.setTranslate(RADIUS - x * ENLARGE_FACTOR  , RADIUS - y * ENLARGE_FACTOR);
        shapeDrawable.getPaint().getShader().setLocalMatrix(mMatrix);

        // 这里抠出对应的放大的图的 放大镜内容区域
        shapeDrawable.setBounds(x - RADIUS,y - RADIUS, x + RADIUS, y + RADIUS);
        invalidate();
        return true;
    }
}
