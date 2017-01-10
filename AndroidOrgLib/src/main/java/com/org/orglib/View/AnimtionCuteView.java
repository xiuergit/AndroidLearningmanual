package com.org.orglib.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.org.orglib.Math.ZPoint;
import com.org.orglib.R;

import java.util.List;

/**
 * Created by xiuer on 16/12/5.
 */

public class AnimtionCuteView extends View {

    //是否摇摆
    public boolean mIsSway;


    int leftArmX = 0;
    int leftArmY = 0;
    int pX = 0;
    int py = 0;
    int p1X = 0;
    int p1y = 0;

    private List<ZPoint> mLeftArmPs;
    private List<ZPoint> mRightArmPs;


    private int height;

    private Paint mPaint;

    public AnimtionCuteView(Context context) {
        super(context);
        init();
    }

    public AnimtionCuteView(Context context, AttributeSet attrs) {

        super(context, attrs);
        init();
    }

    public AnimtionCuteView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
    }


    public void startAnim() {
        // ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(this,"")


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.laogong);


        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        int viewWidth = this.getWidth();
        int viewHeight = this.getHeight();
        canvas.drawBitmap(bitmap, (viewWidth - width) / 2, 0,
                mPaint);


        mPaint.setStrokeWidth(10);
        // paint.setColor(Color.TRANSPARENT);
        int leftSX = viewWidth / 2 - width / 4 + 20;
        int leftSY = height - 20;
        int centerSX = leftSX + width / 4;
        int centerSY = leftSY + 200;
        int rightSX = leftSX + width / 2;
        int rightSY = leftSY - 20;

        //canvas.drawLine(rightSX,rightSY,rightCX,rightCY,paint);

        Path path = new Path();
        path.moveTo(leftSX, leftSY);
        path.quadTo(centerSX, centerSY, rightSX, rightSY);
        //path.cubicTo(leftCX,leftCY,rightCX,rightCY,rightSX,rightSY);
        mPaint.setColor(Color.argb(255, 255, 229, 210));
        canvas.drawPath(path, mPaint);

        mPaint.setColor(Color.BLACK);
        path.reset();

        Path path1 = new Path();

        path1.moveTo(leftSX, leftSY);
        path1.lineTo(centerSX, centerSX);
        path1.lineTo(rightSX, rightSY);

        canvas.drawPath(path1, mPaint);


        path.reset();

        int leftCX = leftSX - 150;
        int leftCY = leftSY + viewHeight / 4;
        int rightCX = rightSX + 200;
        int rightCY = leftCY;

        int centerCX = centerSX;
        int centerCY = rightCY + 20;

        path.moveTo(leftSX, leftSY);

        path.quadTo(leftCX, leftCY, centerCX, centerCY);
        path.moveTo(rightSX, rightSY);

        path.quadTo(rightCX, rightCY, centerCX, centerCY);
        canvas.drawPath(path, mPaint);


        mPaint.setColor(Color.BLUE);
        leftArmX = leftSX - 200;
        //leftArmY=leftSY+viewHeight/8;
        leftArmY = (int) (Math.random() * 100 + leftSY + viewHeight / 8);
        pX = leftSX - 100;
        py = leftArmY - 30;
        p1X = leftSX - 140;
        p1y = leftArmY - 30;

        //paint.setXfermode(new Xfermode());
        path.reset();
        path.moveTo(leftSX, leftSY);
        path.cubicTo(pX, py, p1X, p1y, leftArmX, leftArmY);
        path.lineTo(leftArmX - 10, leftArmY - 30);
        canvas.drawPath(path, mPaint);

        //invalidate();


//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            paint.setColor(Color.BLACK);
//            paint.setStyle(Paint.Style.STROKE);
//            //canvas.drawArc(leftSX,leftSY,rightSX,rightCY,0,180,true,paint);
//        }


    }
}
