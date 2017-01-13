package com.org.xiuer.androidlearningmanual.NetWork;

import android.util.Log;

import com.google.gson.Gson;
import com.org.xiuer.androidlearningmanual.model.AndroidTheory.Catalog;
import com.org.xiuer.androidlearningmanual.model.AndroidTheory.THomeModel;
import com.org.xiuer.androidlearningmanual.model.MainGuides;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

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
                                   final THomeModel model=gson.fromJson(jsonArray.get(i).toString(),THomeModel.class);

                                   BmobQuery queryCatalog=new BmobQuery("Catalog");
                                   queryCatalog.addWhereRelatedTo("catalog",new BmobPointer(model));
//                                   queryCatalog.findObjects(new FindListener() {
//                                       @Override
//                                       public void done(List list, BmobException e) {
//                                           if(e==null){
//                                               Log.i("www", "done: "+list.size());
//                                           }
//                                           else {
//
//                                               Log.i("www", "done: "+e.getMessage());
//                                           }
//                                       }
//
//                                       @Override
//                                       public void done(Object o, Object o2) {
//
//                                       }
//                                   });
                                   queryCatalog.findObjectsByTable(new QueryListener<JSONArray>() {
                                       @Override
                                       public void done(JSONArray jsonArray, BmobException e) {

                                           Log.i("www", "done: "+jsonArray.length());

                                           Gson gson=new Gson();
                                           for (int i=0;i<jsonArray.length();i++){
                                               try {
                                                   Catalog catalog=gson.fromJson(jsonArray.get(i).toString(),Catalog.class);

//                                                   Log.i("wwww", "done: "+catalog.getContent());
//                                                   BmobFile file=catalog.getContent();
//                                                   if(file!=null){
//                                                       Log.i("www", "done: "+file.getUrl());
//                                                   }


                                                   model.getCatalogs().add(catalog);
                                               } catch (JSONException e1) {
                                                   e1.printStackTrace();
                                               }


                                           }
                                           tHomeModels.add(model);

                                           homeModelIBombsResult.onSuccess(tHomeModels);
                                       }
                                   });



                               } catch (JSONException e1) {
                                   e1.printStackTrace();
                               }

                           }



                      }
                else {
                         homeModelIBombsResult.onFailed(e);
                      }
            }
        });


    }

}
