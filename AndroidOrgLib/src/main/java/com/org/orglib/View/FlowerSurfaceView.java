package com.org.orglib.View;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.org.orglib.Math.Algol;
import com.org.orglib.Math.ZPoint;
import com.org.orglib.R;
import com.org.orglib.Util.ImageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by xiuer on 16/12/7.
 */

public class FlowerSurfaceView extends SurfaceView {

    private static String TAG = "AndroidOrg";

    protected SurfaceHolder mSufaceHolder;

    protected Path mPath;//路径

    protected Paint mPaint;//笔


    protected ZPoint mStartPoint;//起点


    protected int mPhase = 0;
    //曲度（0f－1f）
    protected float mT = 0.6f;

    //当前花的index
    protected float mFlower = 0;

    //总共曲线的集合
    protected List<List<ZPoint>> mCurvePaths;


    //测量 曲线上的点
    protected PathMeasure mPathMeasure;


    public FlowerSurfaceView(Context context) {
        super(context);
        init();
    }


    public FlowerSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FlowerSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init() {

        mPath = new Path();

        mSufaceHolder = this.getHolder();

        //初始化笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(5);


    }

    public void initValue() {
        Log.i(TAG, "initValue: ");
        mT = 0.4f;
        mFlower = 0;
        //初始化值
        List<ZPoint> points = getmCurvePoints(10, new ZPoint(800, 20), mT);
        List<ZPoint> points1 = getmCurvePoints(16, new ZPoint(200, 20), 0.6f);
        List<ZPoint> points2 = getmCurvePoints(14, new ZPoint(500, 20), 0.2f);
        mCurvePaths = new ArrayList<>();

        mCurvePaths.add(points);
        mCurvePaths.add(points1);
        mCurvePaths.add(points2);


        for (int i = 0; i < 10; i++) {

            ZPoint start = new ZPoint((float) Math.random() * this.getLayoutParams().width, 20);

            List<ZPoint> pointsnew = getmCurvePoints(20, start, mT);

            mCurvePaths.add(pointsnew);

        }

        mPath = pathCurve(points);
        mPathMeasure = new PathMeasure(mPath, false);

        startAnimation();

    }


    public void startAnimation() {

        ObjectAnimator animator = ObjectAnimator.ofFloat(this,
                "mFlower", 0, mPathMeasure.getLength());
        animator.setDuration(10000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //曲线上下一个点与上一个点的距离
                mFlower = (float) valueAnimator.getAnimatedValue("mFlower");
                invalidate();
            }
        });
        animator.start();
    }


    @Override
    public void draw(Canvas canvas) {


        super.draw(canvas);

        Log.i(TAG, "draw: ");
        if (mPath == null) {
            mPath = new Path();
        }

        //撒花
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.link);
        float bitmapWidth = bitmap.getWidth() * 0.2f * (float) Math.random();
        float bitmapHeight = bitmap.getHeight() * 0.2f * (float) Math.random();
        Bitmap fu = ImageFactory.compressImageBySize(bitmap, bitmapWidth, bitmapHeight);

        float pos[] = new float[2];
        float tan[] = new float[2];
        for (List<ZPoint> points : mCurvePaths) {
            //绘制曲线
            mPath = pathCurve(points);
            mPathMeasure.setPath(mPath, false);
            mPathMeasure.getPosTan(mFlower, pos, tan);
            //画福
            canvas.drawBitmap(fu, pos[0], pos[1], mPaint);
        }
    }

    /**
     * 返回曲线上的所有点的集合
     *
     * @param curvePointCount //点的个数
     * @param startPoint      //起点
     * @param mt              //曲度 0f－1f
     * @return
     */
    public List<ZPoint> getmCurvePoints(int curvePointCount, ZPoint startPoint, float mt) {

        ArrayList mCurvePoints = new ArrayList<>();

        //当前点
        ZPoint point = new ZPoint(0, 0, 0, 0);

        //后继
        ZPoint nextPoint = new ZPoint(0, 0);

        //随机
        Random random = new Random();


        for (int i = 0; i < curvePointCount; i++) {
            //添加的点
            ZPoint myPoint = new ZPoint();

            //宽 需要重新设置 算法
            float width = this.getLayoutParams().width / curvePointCount;
            float height = this.getLayoutParams().height / curvePointCount;


            if (i == 0) {
                //起点
                myPoint.setmX(startPoint.getmX());
                myPoint.setmY(startPoint.getmY());

                point.setmY(startPoint.getmY());
                point.setmX(startPoint.getmX());

                mCurvePoints.add(i, myPoint);

            } else {

                point.setmX(nextPoint.getmX());
                point.setmY(nextPoint.getmY());

                ZPoint pointnew =
                        Algol.curvePoint(mt, i, curvePointCount, point);
                myPoint.setmX((pointnew.getmX() + nextPoint.getmX()) * (float) Math.random());
                myPoint.setmY(pointnew.getmY() + nextPoint.getmY());
                mCurvePoints.add(i, myPoint);
            }
            nextPoint.setmX(point.getmX() + width);
            nextPoint.setmY(point.getmY() + height);
        }

        return mCurvePoints;
    }


    public Path pathLine(List<ZPoint> points) {

        Path path = new Path();

        for (int i = 0; i < points.size(); i++) {

            ZPoint point = points.get(i);
            if (i == 0) {
                path.moveTo(point.getmX(), point.getmY());
            } else {

                path.lineTo(point.getmX(), point.getmY());
            }


        }
        return path;
    }

    public Path pathCurve(List<ZPoint> points) {

        Path path = new Path();
        ZPoint prePoint;
        ZPoint nextPoint;

        ZPoint p3;

        for (int i = 0; i < points.size(); i++) {
            ZPoint point = points.get(i);
            if (i == 0) {
                path.moveTo(point.getmX(), point.getmY());
            } else if ((i + 4) < points.size()) {
                prePoint = points.get(i + 2);
                nextPoint = points.get(i + 3);

                p3 = points.get(i + 4);
                i++;
                //   path.quadTo(prePoint.getmX(),prePoint.getmY(),nextPoint.getmX(),nextPoint.getmY());
                path.cubicTo(prePoint.getmX(), prePoint.getmY(), nextPoint.getmX(), nextPoint.getmY(), p3.getmX(), p3.getmY());
            }

        }

        return path;
    }


}
