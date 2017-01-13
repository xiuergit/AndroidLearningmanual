package com.org.xiuer.androidlearningmanual.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.org.orglib.Math.ZPoint;
import com.org.xiuer.androidlearningmanual.R;
import com.org.xiuer.androidlearningmanual.model.AndroidTheory.Catalog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangxiu on 2017/1/9.
 */

public class CompositeView extends View   {
    public void setClick(OnItemClickListener click) {
        this.click = click;
    }

    private  OnItemClickListener click;
    private List<Catalog>datas;
    private  int mWidth;
    private  int mHeight;

    private  String  MainTitle;
    private   int   FontSize;

    private Rect mRect;

    private Paint paint;
    private  List<SubTitle>subtitles=new ArrayList<>();

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

    public List<Catalog> getDatas() {
        return datas;
    }

    public void setDatas(List<Catalog> datas) {
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
        paint.setStyle(Paint.Style.FILL);
        float cx=mWidth/2;
        float cy=mHeight/2;
        float radius=mWidth/6;
        paint.setAntiAlias(false);
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
                Rect rect=new Rect((int) dx1-40,(int)dy1+8,(int)dx1+40,(int)dy1-8);
                SubTitle title=new SubTitle(datas.get(i-1),rect);
                drawSubTitle(canvas,title);
                subtitles.add(title);
            }
        }

         paint.setColor(Color.WHITE);
        canvas.drawText(MainTitle,cx,cy,paint);


    }

    public  void  drawSubTitle(Canvas canvas ,SubTitle title){
        Paint paint=new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(this.FontSize);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.BLUE);
        if(title.getCatalog().getSelect()){
            paint.setColor(Color.RED);
        }
        canvas.drawText(title.getCatalog().getName(), title.getRect().centerX(),title.getRect().centerY(),paint);

        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(title.getRect().left,title.getRect().top,title.getRect().right,title.getRect().bottom,paint);


    }






    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x=event.getX();
        float y=event.getY();
        for (SubTitle title :subtitles){
            if(Math.abs(x-title.getRect().centerX())<80&&Math.abs(y-title.getRect().centerY())<40){
                   List<Catalog>calogs=new ArrayList<>();
                 for (Catalog catalog:datas){
                     if(catalog.getName().equals(title.getCatalog().getName())){
                         catalog.setSelect(true);
                     }else {catalog.setSelect(false);}
                     calogs.add(catalog);
                 }
                this.datas=calogs;
                invalidate(title.getRect());
                click.onItemClick(this, title.getCatalog());

            }
        }

        return false;
    }


    public interface  OnItemClickListener{
         void onItemClick(CompositeView compositeView, Catalog catalog);
    }

    /**
     * 每个子标题的信息
     */
    class  SubTitle{
        /**
         * 子标题
         */
        private  Catalog catalog;
        /**
         *  范围位置
         */
        private  Rect rect;

        public Rect getRect() {
            return rect;
        }

        public void setRect(Rect rect) {
            this.rect = rect;
        }

        public SubTitle(Catalog catalog, Rect rect) {
            this.catalog = catalog;
            this.rect = rect;
        }

        public Catalog getCatalog() {
            return catalog;
        }

        public void setCatalog(Catalog catalog) {
            this.catalog = catalog;
        }

        @Override
        public String toString() {
            return "SubTitle{" +
                    "catalog=" + catalog +
                    ", rect=" + rect +
                    '}';
        }
    }

}

