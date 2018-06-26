package com.iigo.paintshader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author SamLeung
 * @Emial 729717222@qq.com
 * @date 2018/6/26 0026 17:58
 */
public class ComposeShaderView extends View {
    private Bitmap bitmap;
    private ComposeShader composeShader;
    private Paint paint;

    public ComposeShaderView(Context context) {
        super(context);

        init();
    }

    public ComposeShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    public ComposeShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init(){
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.heart);

        //我们创建了一个位图图像渲染器，和一个线性渲染器，再通过ComposeShader将两者以 'mode' 这种混合模式进行混合
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        LinearGradient linearGradient = new LinearGradient(0, 0, bitmap.getWidth(), 0, new int[]{Color.RED, Color.WHITE, Color.YELLOW}, null, Shader.TileMode.CLAMP);

        composeShader = new ComposeShader(bitmapShader, linearGradient, PorterDuff.Mode.MULTIPLY); //直接叠加

        paint = new Paint();
        paint.setShader(composeShader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (bitmap == null
                || composeShader == null){
            return;
        }

        canvas.drawRect(0, 0, bitmap.getWidth(), bitmap.getHeight(), paint);
    }
}
