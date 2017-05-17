package com.example.breatheview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

/**
 * author：JianFeng
 * date：2017/5/17 18:30
 * description：
 */
public class BreatheView extends View implements ValueAnimator.AnimatorUpdateListener {
    /**
     * 扩散圆圈颜色
     */
    private int mDiffusionColor = Color.parseColor("#303F9F");
    /**
     * 圆圈中心颜色
     */
    private int mCoreColor = Color.parseColor("#FF4081");

    /**
     * 中心圆半径
     */
    private float mCoreRadius = 30f;

    /**
     * 整个绘制面的最大宽度
     */
    private float mMaxWidth = 40f;

    /***
     * 初始透明度
     */
    private int color = 255;

    /**
     * 是否正在扩散中
     */
    private boolean mIsDiffuse = false;
    private Paint mPaint;
    private float mFraction;//变化因子
    private ValueAnimator mAnimator;
    private long HEART_BEAT_RATE = 2000;//每隔多久闪烁一次
    private Handler mHandler;
    //圆圈中心坐标
    private float circleX;
    private float circleY;
    private int durrarion = 2000; //闪烁持续时间


    public BreatheView(Context context) {
        this(context, null);
    }

    public BreatheView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BreatheView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, context);
    }

    private void init(AttributeSet attrs, Context context) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.BreatheView);
        if (null != array) {
            durrarion = array.getInt(R.styleable.BreatheView_duration, 2000);
            array.recycle();
        }
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);
        mAnimator = ValueAnimator.ofFloat(0, 1f).setDuration(durrarion);
        mAnimator.addUpdateListener(this);
        if (null == mHandler) {
            mHandler = new Handler();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        circleX = w / 2;
        circleY = h / 2;
    }


    /***
     * 设置中心圆半径
     *
     * @param radius
     */
    public BreatheView setCoreRadius(float radius) {
        mCoreRadius = radius;
        return this;
    }

    /***
     * 设置扩散圆圈最大半径
     *
     * @param width
     */
    public BreatheView setDiffusMaxWidth(float width) {
        mMaxWidth = width;
        return this;
    }

    /***
     * 设置圆绘制的坐标
     * @param x
     * @param y
     */
    public BreatheView setCoordinate(float x, float y) {
        circleX = x;
        circleY = y;
        return this;
    }

    /***
     * 设置扩散圆的半径
     * @param color
     */
    public BreatheView setDiffusColor(int color) {
        mDiffusionColor = color;
        return this;
    }

    /***
     * 设置中心圆的颜色
     * @param coreColor
     */
    public BreatheView setCoreColor(int coreColor) {
        mCoreColor = coreColor;
        return this;
    }

    /***
     * 设置闪烁的间隔时间
     * @param durataion
     */
    public BreatheView setInterval(long durataion) {
        HEART_BEAT_RATE = durataion;
        return this;
    }


    @Override
    public void invalidate() {
        if (hasWindowFocus()) {
            super.invalidate();
        }
    }


    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus) {
            invalidate();
        }
    }

    private Runnable heartBeatRunnable = new Runnable() {
        @Override
        public void run() {
            connect();
            mHandler.postDelayed(this, HEART_BEAT_RATE);
        }
    };

    public BreatheView onStart() {
        mHandler.removeCallbacks(heartBeatRunnable);
        mHandler.post(heartBeatRunnable);
        return this;
    }

    public void onStop() {
        mIsDiffuse = false;
        mHandler.removeCallbacks(heartBeatRunnable);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mIsDiffuse) {
            //绘制扩散圆
            mPaint.setColor(mDiffusionColor);
            mPaint.setAlpha((int) (color - color * mFraction));
            canvas.drawCircle(circleX, circleY, mCoreRadius + mMaxWidth * mFraction, mPaint);
            // 绘制中心圆
            mPaint.setAntiAlias(true);
            mPaint.setAlpha(255);
            mPaint.setColor(mCoreColor);
            canvas.drawCircle(circleX, circleY, mCoreRadius, mPaint);
        }
        invalidate();

    }

    public void connect() {
        mIsDiffuse = true;
        mAnimator.start();
        invalidate();
    }


    @Override
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        mFraction = (float) valueAnimator.getAnimatedValue();
    }
}
