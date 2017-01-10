package com.org.xiuer.androidlearningmanual.View;

import android.animation.FloatArrayEvaluator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.media.tv.TvTrackInfo;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.org.orglib.Math.ZPoint;
import com.org.xiuer.androidlearningmanual.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangxiu on 2017/1/9.
 */

public class CompositeView extends View   {

    private ArrayList<String>datas;
    private  int mWidth;
    private  int mHeight;

    private  String  MainTitle;
    private   int   FontSize;

    private Rect mRect;

    private Paint paint;

    //private  Paint paintSelect;

    /**
     * 每个目录的活动范围
     */
    private List<ZPoint> points=new ArrayList<>();

    public String getMainTitle() {
        return MainTitle;
    }

    public void setMainTitle(String mainTitle) {
        MainTitle = mainTitle;
        invalidate();
    }

    public ArrayList<String> getDatas() {
        return datas;
    }

    public void setDatas(ArrayList<String> datas) {
        this.datas = datas;
        invalidate();
    }



    public void setFontSize(int fontSize) {
        FontSize = fontSize;
    }


    public void init(){

        paint=new Paint();
        paint.setAntiAlias(false);
        mRect=new Rect();
    }
    public CompositeView(Context context) {
        super(context);
         init();


    }

    public CompositeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CompositeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array=context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.CompositeView,defStyleAttr,0);

        for(int i=0;i<array.getIndexCount();i++) {


            switch (array.getIndex(i)) {

                case R.styleable.CompositeView_MainTitle:
                    MainTitle = array.getString(i);
                    break;

            }

        }
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
            //自定义宽
            width=mRect.width()+20;
        }
        if(heightMode==MeasureSpec.EXACTLY){
            height=heightSize;
        }else {
            height=width;
        }

        mWidth=width;
        mHeight=height;

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(0,0,mWidth,mHeight,paint);


        //中心圆圈
        paint.setColor(Color.RED);
        paint.setStrokeWidth(2);
        float cx=mWidth/2;
        float cy=mHeight/2;
        float radius=mWidth/6;
        paint.setAntiAlias(false);
        paint.setColor(Color.BLUE);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawCircle(cx,cy,radius,paint);
        paint.setTextSize(this.FontSize);
        if(datas!=null){
            double v= Math.PI*2/datas.size();
            for(int i=1;i<=datas.size();i++){
                float cx1=(float) Math.cos(v*i)*radius+cx;
                float cy1=(float) Math.sin(v*i)*radius+cy;
                float dx=(float) Math.cos(v*i)*(radius+radius/2)+cx;
                float dy=(float) Math.sin(v*i)*(radius+radius/2)+cy;
                float dx1=(float) Math.cos(v*i)*(radius*2)+cx;
                float dy1=(float) Math.sin(v*i)*(radius*2)+cy;
                canvas.drawLine(cx1,cy1,dx,dy,paint);
                canvas.drawText(datas.get(i-1),dx1,dy1,paint);
                ZPoint point=new ZPoint(dx1,dy1);
                points.add(point);

            }
        }


        canvas.drawText(MainTitle,cx,cy,paint);


    }


    public  class  SubTitle{
        private  boolean  isSelect;

    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {


        float x=event.getX();
        float y=event.getY();
        for (ZPoint point :points){
            if(Math.abs(x-point.getmX())<40&&Math.abs(y-point.getmY())<8){
                Log.i("Composite", "onTouchEvent: "+"X:"+event.getX()+"Y:"+event.getY());
                Log.i("Composite", "onTouchEvent: "+point);

            }

        }



        return false;
    }
}
