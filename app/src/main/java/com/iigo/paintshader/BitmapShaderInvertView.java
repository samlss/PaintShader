package com.iigo.paintshader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author SamLeung
 * @Emial 729717222@qq.com
 * @date 2018/6/26 0026 17:08
 */
public class BitmapShaderInvertView extends View {
    private Bitmap bitmap;
    private BitmapShader bitmapShader;
    private ShapeDrawable shapeDrawable;

    public BitmapShaderInvertView(Context context) {
        super(context);
        init();
    }

    public BitmapShaderInvertView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BitmapShaderInvertView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.head);
        bitmapShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.MIRROR);
        shapeDrawable = new ShapeDrawable(new OvalShape());

        shapeDrawable.getPaint().setShader(bitmapShader);
//        shapeDrawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight()); //原图大小
        shapeDrawable.setBounds(0, 0, bitmap.getWidth() * 2, bitmap.getHeight() * 2); //原图大小
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        shapeDrawable.draw(canvas);
    }
}
