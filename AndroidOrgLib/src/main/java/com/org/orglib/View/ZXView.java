package com.org.orglib.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;

import com.org.orglib.R;

/**
 * Created by xiuer on 16/9/6.
 */
public class ZXView extends Button {

    private static String TAG="ZXView";

    private  String mTitleText;

    private  int mTitleSize;

    private  int mTitleColor;

    private  Paint mPaint;

    private Rect mRect;

    public ZXView(Context context) {

        this(context,null);
    }


    public ZXView(Context context, AttributeSet attrs) {
        this(context, attrs,0);


    }

    public ZXView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        TypedArray array=context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.ZXView,defStyleAttr,0);

        for(int i=0;i<array.getIndexCount();i++){


            switch (array.getIndex(i)){

//                case R.styleable.ZXView_titleText:
//                    mTitleText=array.getString(i);
//                    break;
//                case R.styleable.ZXView_titleSize:
//                    mTitleSize=array.getDimensionPixelSize(i,16);
//                    break;
//                case  R.styleable.ZXView_titleColor:
//                    mTitleColor=array.getColor(i,Color.GRAY);
//                    break;
            }

        }

        mRect=new Rect();
        mPaint=new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(mTitleSize);
        mPaint.getTextBounds(mTitleText,0,mTitleText.length(),mRect);







    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        Log.i(TAG, "onLayout: ");

        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width,height;

        int  widthMode=MeasureSpec.getMode(widthMeasureSpec);

        int  widthSize=MeasureSpec.getSize(widthMeasureSpec);

        int heightMode=MeasureSpec.getMode(heightMeasureSpec);

        int heightSize=MeasureSpec.getSize(heightMeasureSpec);
        //充满父控件
        if(widthMode==MeasureSpec.EXACTLY){
            width=widthSize;

        }else {

            width=mRect.width()+20;

        }
        if(heightMode==MeasureSpec.EXACTLY){
            height=heightSize;
        }else {
            height=width;
        }


        Log.i(TAG, "onMeasure: "+"width:"+width+"height:"+
                height);

        setMeasuredDimension(width, height);
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.i(TAG, "onDraw: ");
        mPaint.setColor(Color.YELLOW);
        canvas.drawCircle(getMeasuredWidth()/2,getMeasuredHeight()/2,getMeasuredWidth()/2,mPaint);
        mPaint.setColor(mTitleColor);
        canvas.drawText(mTitleText,0,getMeasuredHeight()/2+mRect.height()/2,mPaint);

    }

    public String getmTitleText() {
        return mTitleText;
    }

    public void setmTitleText(String mTitleText) {
        this.mTitleText = mTitleText;
        invalidate();
    }

    public int getmTitleSize() {
        return mTitleSize;
    }

    public void setmTitleSize(int mTitleSize) {
        this.mTitleSize = mTitleSize;
    }

    public int getmTitleColor() {
        return mTitleColor;
    }

    public void setmTitleColor(int mTitleColor) {
        this.mTitleColor = mTitleColor;
    }

    public Paint getmPaint() {
        return mPaint;
    }

    public void setmPaint(Paint mPaint) {
        this.mPaint = mPaint;
    }

    @Override
    public String toString() {
        return "ZXView{" +
                "mRect=" + mRect +
                ", mTitleColor=" + mTitleColor +
                ", mTitleSize=" + mTitleSize +
                ", mTitleText='" + mTitleText + '\'' +
                '}';
    }
}
