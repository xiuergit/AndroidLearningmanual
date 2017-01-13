package com.org.xiuer.androidlearningmanual;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.org.xiuer.androidlearningmanual.Adapter.LeftAdapter;
import com.org.xiuer.androidlearningmanual.Fragment.AndroidTheory.HomeFragment_theory;
import com.org.xiuer.androidlearningmanual.Fragment.Content_Fragment;
import com.org.xiuer.androidlearningmanual.Interface.ItemClickListener;
import com.org.xiuer.androidlearningmanual.NetWork.IBombsResult;
import com.org.xiuer.androidlearningmanual.NetWork.LoadBombs;
import com.org.xiuer.androidlearningmanual.model.AndroidTheory.THomeModel;
import com.org.xiuer.androidlearningmanual.model.Guide;
import com.org.xiuer.androidlearningmanual.model.MainGuides;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

//import android.support.v4.widget.DrawerLayout;

@ContentView(R.layout.activity_load)
public class LoadActivity extends BaseActivity implements
        Content_Fragment.OnFragmentInteractionListener{

    static  String TAG="LoadActivity";
    //目录清单
    private ArrayList<MainGuides>guides=new ArrayList<>();


    //Google 提供的 侧滑菜单
    @ViewInject(R.id.drawer_layout)
    private DrawerLayout mDrawerLayout;


    private DrawerLayout.SimpleDrawerListener simpleDrawerListener;


    //左边目录
    @ViewInject(R.id.left_view)
    private LinearLayout mLView;

    //左边的标题
    @ViewInject(R.id.left_tool)
    private Toolbar mLeftToolbar;

    //显示目录列表
    @ViewInject(R.id.left_recyclerView)
    private  RecyclerView mLeftView;


    //内容标题
    @ViewInject(R.id.center_content_tool)
    public Toolbar mCenterTool;

    //内容
    @ViewInject(R.id.center_content_frame)
    private FrameLayout mCenterLayout;

    /**
     * 初始化目录列表内容
     */
    protected  void initList(){
      LoadBombs.loadMainGuides(new IBombsResult<MainGuides>() {
          @Override
          public void onSuccess(final List<MainGuides> bombs) {

              mLeftView.setAdapter(new LeftAdapter(bombs, new ItemClickListener() {
                  @Override
                  public void onItemClick(View view, int position) {
                      //当前选个哪个就加载其内容
                      String title=bombs.get(position).getTitle();
                      mCenterTool.setTitle(title);
                      loadCenterContent(position);
                  }
                  @Override
                  public void onLongItemClick(View view, int position) {
                      Log.i(TAG, "onLongItemClick: ");
                  }
              }));
          }

          @Override
          public void onFailed(BmobException e) {
              Log.i(TAG, "onFailed: "+e.getMessage());
          }
      });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         initList();
         initUIData();
    }

    protected  void initUIData(){
        mLeftToolbar.setTitle("android学习手册");
        //默认加载第一项
        loadCenterContent(0);
        mCenterTool.setTitle("Android基础理论");
        RecyclerView.LayoutManager manager=new LinearLayoutManager(this);
        mLeftView.setLayoutManager(manager);

    }

    /**
     * 根据向导加载内容，默认为第一个
     * @param position
     */
     public void  loadCenterContent(int position){
         if(position==0){

             LoadBombs.loadTHomeModel(new IBombsResult<THomeModel>() {
                 @Override
                 public void onSuccess(List<THomeModel> bombs) {
                     Log.i(TAG, "onSuccess: "+bombs);
                     HomeFragment_theory homeFragment=HomeFragment_theory.newInstance((ArrayList<THomeModel>) bombs);
                     FragmentTransaction transaction= getFragmentManager().beginTransaction();
                     transaction.replace(R.id.center_content_frame,homeFragment);
                     transaction.commit();
                 }

                 @Override
                 public void onFailed(BmobException e) {
                     Log.i(TAG, "onFailed: "+e.getMessage());
                 }
             });
         }
         mDrawerLayout.closeDrawer(mLView);
         if(position==3){
              startActivity(new Intent(this,TestActivity.class));
         }



     }


    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.i(TAG, "onFragmentInteraction: "+uri);
    }
}
