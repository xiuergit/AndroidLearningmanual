package com.org.xiuer.androidlearningmanual.Adapter.AndroidTheory;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.org.xiuer.androidlearningmanual.View.CompositeView;
import com.org.xiuer.androidlearningmanual.model.AndroidTheory.Catalog;
import com.org.xiuer.androidlearningmanual.model.AndroidTheory.THomeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页GridView的适配器
 * Created by zhangxiu on 2017/1/9.
 */

public class HomeAdapter  extends BaseAdapter{
     private static String TAG="HomeAdapter";
     private ICompositeOnItemClick onItemClick;

    public ICompositeOnItemClick getOnItemClick() {
        return onItemClick;
    }

    public void setOnItemClick(ICompositeOnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    private Context context;
    private List<THomeModel> homeDatas=new ArrayList();

    public List<THomeModel> getHomeDatas() {
        return homeDatas;
    }

    public void setHomeDatas(List<THomeModel> homeDatas) {
        this.homeDatas = homeDatas;
    }

    public HomeAdapter(Context context, List<THomeModel> homeDatas,ICompositeOnItemClick click) {
        this.context = context;
        this.homeDatas = homeDatas;
        this.onItemClick=click;
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

        final CompositeView  compositeView=new CompositeView(context);
        compositeView.setBackgroundColor(Color.WHITE);

         compositeView.setClick(new CompositeView.OnItemClickListener() {
             @Override
             public void onItemClick(CompositeView compositeView, Catalog catalog) {
                  onItemClick.onItemClick(compositeView,catalog);
             }
         });
        compositeView.setDatas(mode.getCatalogs());
        compositeView.setMainTitle(mode.getName());
        compositeView.setFontSize(36);
        return compositeView;
    }

    public interface ICompositeOnItemClick {
         void  onItemClick(CompositeView view,Catalog catalog);
    }

}
