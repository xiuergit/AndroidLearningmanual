package com.org.xiuer.androidlearningmanual.NetWork;

import android.util.Log;

import com.google.gson.Gson;
import com.org.xiuer.androidlearningmanual.model.AndroidTheory.THomeModel;
import com.org.xiuer.androidlearningmanual.model.MainGuides;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.socketio.callback.ErrorCallback;
import rx.internal.schedulers.NewThreadScheduler;

/**
 * Created by zhangxiu on 2017/1/9.
 */

public class LoadBombs {
    /**
     * 加载向导 数据 MainGuides 表里的数据
     * @param iBombsResult
     */
    public  static void loadMainGuides( final IBombsResult<MainGuides> iBombsResult ){
        final List<MainGuides>mainGuides=new ArrayList<>();
        BmobQuery query=new BmobQuery("MainGuides");
        query.findObjectsByTable(new QueryListener<JSONArray>() {
            @Override
            public void done(JSONArray jsonArray, BmobException e) {
                if(e==null){
                    Gson gson=new Gson();
                    for (int i=0;i<jsonArray.length();i++){
                        try {
                            MainGuides guide=gson.fromJson(jsonArray.getJSONObject(i).toString(),MainGuides.class);
                            mainGuides.add(guide);
                            iBombsResult.onSuccess(mainGuides);

                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }
                    }
        }
                else {
                    iBombsResult.onFailed(e);
                }
            }});



    }



    public static void loadTHomeModel(final IBombsResult<THomeModel>homeModelIBombsResult){
        BmobQuery bmobQuery=new BmobQuery("THomeModel");
        final List<THomeModel>tHomeModels=new ArrayList<>();
        bmobQuery.findObjectsByTable(new QueryListener<JSONArray>() {
            @Override
            public void done(JSONArray jsonArray, BmobException e) {
                      if(e==null){
                          Gson gson=new Gson();
                           for(int i=0;i<jsonArray.length();i++){
                               try {
                                   THomeModel model=gson.fromJson(jsonArray.get(i).toString(),THomeModel.class);
                                   tHomeModels.add(model);
                               } catch (JSONException e1) {
                                   e1.printStackTrace();
                               }

                           }
                          homeModelIBombsResult.onSuccess(tHomeModels);


                      }
                else {
                         homeModelIBombsResult.onFailed(e);
                      }
            }
        });


    }

}
