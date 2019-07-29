package com.yikangcheng.admin.yikang.activity.liveambitus.city;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by YH on 2016/10/19.
 */

public class CircleTextView extends TextView {
    private Paint mBgPaint = new Paint();
    private PaintFlagsDrawFilter pfd = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG| Paint.FILTER_BITMAP_FLAG);
    public CircleTextView(Context context) {
        super(context);
    }

    public CircleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mWidth = getMeasuredWidth();
        int mHeight = getMeasuredHeight();
        int max = Math.max(mWidth,mHeight);

        setMeasuredDimension(max,max);
    }

    @Override
    public void draw(Canvas canvas) {

        canvas.setDrawFilter(pfd);
        canvas.drawCircle(getWidth()/2,getHeight()/2, Math.max(getWidth(),getHeight())/2,mBgPaint);
        super.draw(canvas);

    }

    public void setBackGroundColor(int color){
        mBgPaint.setColor(color);
    }
}
