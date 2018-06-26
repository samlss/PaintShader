package com.iigo.paintshader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class SweepGradientRadarView extends View {
    private int viewWidth, viewHeight; //view的宽高
    private float[] RADIUS_RATIO = {0.05f, 0.1f, 0.15f, 0.2f, 0.25f, 0.30f}; //六个圆的半径比例

    private SweepGradient sweepGradient; // 扫描渲染器
    private Matrix rotateMatrix = new Matrix(); //旋转需要的矩阵
    private int rotateDelta = 10; //每次旋转增加的角度，影响旋转速度
    private int rotateAngle; //扫描层旋转的角度

    private Paint circlePaint; //画圆的画笔
    private Paint radarPaint; //画雷达的画笔


    public SweepGradientRadarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SweepGradientRadarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public SweepGradientRadarView(Context context) {
        super(context);
        init();
    }

    private void init(){
        //画圆的画笔
        circlePaint = new Paint();
        circlePaint.setStyle(Paint.Style.STROKE); //描边
        circlePaint.setStrokeWidth(1); //描边宽度
        circlePaint.setAntiAlias(true); //抗锯齿
        circlePaint.setColor(Color.RED);

        //画雷达效果的画笔
        radarPaint = new Paint();
        radarPaint.setStyle(Paint.Style.FILL_AND_STROKE); //填充
        radarPaint.setAntiAlias(true); //抗锯齿

        //开始执行雷达动画任务
        post(task);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //先绘制六个圆
        for (int i = 0; i < RADIUS_RATIO.length; i++) {
            canvas.drawCircle(viewWidth / 2, viewHeight / 2, viewWidth * RADIUS_RATIO[i], circlePaint);
        }

        //画布旋转，需要先保存状态，转换完之后还原
        canvas.save();

        //这里定义的扫描渲染器，设置起始的颜色为透明值，结束的颜色值为绿色
        sweepGradient = new SweepGradient(viewWidth / 2, viewHeight / 2, Color.TRANSPARENT,
                Color.GREEN);

        radarPaint.setShader(sweepGradient); //设置着色器
        //将扫描着色器这一次进行旋转
        canvas.setMatrix(rotateMatrix); //设置旋转矩阵

        //直接画渲染最大圆
        canvas.drawCircle(viewWidth / 2, viewHeight / 2, viewWidth * RADIUS_RATIO[RADIUS_RATIO.length - 1], radarPaint);

        //还原画布状态
        canvas.restore();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //初始化view的宽高，以将雷达放置到view中心位置
        viewWidth = getMeasuredWidth();
        viewHeight = getMeasuredHeight();
    }

    private Runnable task = new Runnable() {
        @Override
        public void run() {
            //每次增加rotateDelta角度
            rotateAngle = (rotateAngle + rotateDelta) % 360; // 旋转角度 对360取余

            //设置旋转角度和旋转中心位置
            rotateMatrix.postRotate(rotateDelta, viewWidth / 2, viewHeight / 2);

            invalidate(); //重绘
            postDelayed(task, 50); //延迟50ms后，再次进行绘制
        }
    };
}
