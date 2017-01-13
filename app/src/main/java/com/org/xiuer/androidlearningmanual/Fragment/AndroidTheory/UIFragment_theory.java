package com.org.xiuer.androidlearningmanual.Fragment.AndroidTheory;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.util.EventLog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.org.xiuer.androidlearningmanual.Adapter.AndroidTheory.UIAdapter;
import com.org.xiuer.androidlearningmanual.LoadActivity;
import com.org.xiuer.androidlearningmanual.NetWork.HttpGet;
import com.org.xiuer.androidlearningmanual.R;
import com.org.xiuer.androidlearningmanual.model.AndroidTheory.Catalog;
import com.org.xiuer.androidlearningmanual.model.AndroidTheory.UIModel;

import java.io.IOException;

import cn.bmob.v3.b.I;

/**
 * UI界面 包含了textView surfavaView 。。。
 */
public class UIFragment_theory extends Fragment {
    private static final String ARG_TITLE = "title";
    private  static  final  String TAG="UIFragment_theory";

    private  static  final int RequestSuccess=0x123;
    private  static  final int RequestFailure=0x124;

    private String mTitle;

    public Catalog getmCatalog() {
        return mCatalog;
    }

    public void setmCatalog(Catalog mCatalog) {
        this.mCatalog = mCatalog;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    private Catalog mCatalog;
    //要显示的数据
    private  UIModel model;


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
           if(msg.what==RequestFailure){

           }else if(msg.what==RequestSuccess){
               updateUIDates(model);
           }
        }
    };


    ///ui
    private TextView mIntroductionView;
    private ListView mAttrsView;
    private GridView mSubView;


    public UIFragment_theory() {
        // Required empty public constructor
    }

    /***
     * 实例化
     * @param mTitle
     * @return
     */
    public static UIFragment_theory newInstance(String mTitle) {
        UIFragment_theory fragment = new UIFragment_theory();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, mTitle);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 更新ui的数据
     */
    public  void updateUIDates(UIModel model){
        mIntroductionView.setText(model.getName());
        mAttrsView.setAdapter(new UIAdapter(getActivity(),model));

    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(TAG, "onViewCreated: ");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTitle = getArguments().getString(ARG_TITLE);
            LoadActivity activity=(LoadActivity)getActivity();
            activity.mCenterTool.setTitle(mTitle);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_uifragment_theory, container, false);
        TextView textView= (TextView) view.findViewById(R.id.ui_tv_title);
        textView.setText(mCatalog.getName());
        mIntroductionView=(TextView)view.findViewById(R.id.ui_tv_des);
        mAttrsView=(ListView)view.findViewById(R.id.ui_lv_attr);
        mSubView=(GridView)view.findViewById(R.id.gv_home_theory);
        return  view;
    }






    // TODO: Rename method, update argument and hook method into UI event
        public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG, "onAttach: "+mCatalog);
        HttpGet<UIModel> modelHttpGet=new HttpGet<>();
        modelHttpGet.HttpRequestJsonTOModel(mCatalog.getContent().getUrl(), UIModel.class, new HttpGet.ResultCallback<UIModel>() {
            @Override
            public void OnSuccess(Object o) {
                model=(UIModel) o;
                Log.i(TAG, "OnSuccess: "+model);
                handler.sendEmptyMessage(RequestSuccess);
            }

            @Override
            public void OnFailure(IOException e) {
                Log.i(TAG, "OnFailure: "+e.getMessage());
                handler.sendEmptyMessage(RequestFailure);
            }
        });

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "onDetach: ");
       // mListener = null;
    }

}
