package com.org.xiuer.androidlearningmanual.Adapter.AndroidTheory;

import android.content.Context;
import android.graphics.Color;
import android.print.PageRange;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.org.xiuer.androidlearningmanual.View.CompositeView;
import com.org.xiuer.androidlearningmanual.model.AndroidTheory.THomeModel;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

import cn.bmob.v3.a.a.This;

/**
 * 首页GridView的适配器
 * Created by zhangxiu on 2017/1/9.
 */

public class HomeAdapter  extends BaseAdapter{
     private static String TAG="HomeAdapter";

    private Context context;
    private List<THomeModel> homeDatas=new ArrayList();

    public List<THomeModel> getHomeDatas() {
        return homeDatas;
    }

    public void setHomeDatas(List<THomeModel> homeDatas) {
        this.homeDatas = homeDatas;
    }

    public HomeAdapter(Context context, List<THomeModel> homeDatas) {
        this.context = context;
        this.homeDatas = homeDatas;
    }

    public HomeAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        if(homeDatas.size()>0){
            return  homeDatas.size();
        }
        else{
            return 0;
        }

    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        THomeModel mode= this.homeDatas.get(i);

         TextView V=new TextView(context);

        V.setText(this.getHomeDatas().get(i).getName());

        CompositeView  compositeView=new CompositeView(context);
        compositeView.setBackgroundColor(Color.WHITE);
        compositeView.setDatas((ArrayList<String>) mode.getCatalogs());
        compositeView.setMainTitle(mode.getName());
        compositeView.setFontSize(36);
        return compositeView;
    }
}
