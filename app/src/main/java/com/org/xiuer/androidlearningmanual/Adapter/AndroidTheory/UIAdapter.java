package com.org.xiuer.androidlearningmanual.Adapter.AndroidTheory;

import android.content.Context;
import android.print.PageRange;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.org.xiuer.androidlearningmanual.R;
import com.org.xiuer.androidlearningmanual.model.AndroidTheory.UIModel;

/**
 * UIListView的适配器
 * Created by zhangxiu on 2017/1/13.
 */
public class UIAdapter extends BaseAdapter {

  private Context mContext;
  private UIModel  mUiModel;

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public UIModel getmUiModel() {
        return mUiModel;
    }

    public void setmUiModel(UIModel mUiModel) {
        this.mUiModel = mUiModel;
    }

    public UIAdapter(Context mContext, UIModel mUiModel) {
        this.mContext = mContext;
        this.mUiModel = mUiModel;
    }

    @Override
    public int getCount() {
        if(mUiModel.getAttributes().size()>0){
            return  mUiModel.getAttributes().size();
        }
        else {
            return  0;
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
         View v= LinearLayout.inflate(mContext, R.layout.listview_item_uitheory,null);

        TextView view1=(TextView) v.findViewById(R.id.ui_tv_name);
        view1.setText(mUiModel.getAttributes().get(i).getName());
        TextView view2=(TextView) v.findViewById(R.id.ui_tv_att);
        view2.setText(mUiModel.getAttributes().get(i).getMethod());
        TextView view3=(TextView) v.findViewById(R.id.ui_tv_describe);
        view3.setText(mUiModel.getAttributes().get(i).getDescription());


        return v;
    }
}
