package com.org.xiuer.androidlearningmanual;

import android.os.Bundle;
import android.util.Log;

import com.org.xiuer.androidlearningmanual.NetWork.HttpGet;
import com.org.xiuer.androidlearningmanual.model.AndroidTheory.Catalog;

import com.org.xiuer.androidlearningmanual.View.CompositeView;
import com.org.xiuer.androidlearningmanual.model.AndroidTheory.THomeModel;
import com.org.xiuer.androidlearningmanual.model.AndroidTheory.UIModel;


import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobRelation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class TestActivity extends BaseActivity {
     private CompositeView mCompositeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
//        mCompositeView=(CompositeView)findViewById(R.id.compositeView_test);
//
//        ArrayList data=new ArrayList();
//        data.add("123");
//        data.add("TESTQ");
//        data.add("TESTQ");
//        mCompositeView.setDatas(data);
//        mCompositeView.setMainTitle("ui");
//        mCompositeView.setFontSize(15);

//        THomeModel model =new THomeModel();
//        model.setName("test1");
//        model.setId("123");
////        List<Catalog>catalogses=new ArrayList<>();
//
//
//        Catalog catalog=new Catalog();
//        catalog.setName("123");
//        catalog.setSelect(true);
//        Catalog catalog1=new Catalog();
//        catalog1.setName("1234");
//        catalog1.setSelect(false);
////        catalogses.add(catalogs);
//        BmobRelation catalogs=new BmobRelation();
//        catalogs.add(catalog);
//        catalogs.add(catalog1);
//        model.setCatalog(catalogs);

//model.save(new SaveListener<String>() {
//    @Override
//    public void done(String s, BmobException e) {
//        if(e==null){
//            Log.i("123", "done: 成功"+s);
//        }   else {
//            Log.i("dd", "done: "+e.getMessage());
//        }
//    }
//});




        //Test


//        model.setCatalogs(catalogses);
//         model.save(new SaveListener<String>() {
//             @Override
//             public void done(String s, BmobException e) {
//               if(e==null){
//                   Log.i("123", "done: "+s);
//               }   else {
//                   Log.i("dd", "done: "+e.getMessage());
//               }
//
//             }
//         });


//        BmobQuery query=new BmobQuery("THomeModel");
//
//        query.findObjectsByTable(new QueryListener<JSONArray>() {
//            @Override
//            public void done(JSONArray jsonArray, BmobException e) {
//
//                Log.i("ddGGGGG", "done: "+jsonArray);
//            }
//        });
//
//
//      final   Catalog log=new Catalog("TextView",true);
//      final   Test test=new Test("TEST");
//        test.save(new SaveListener<String>() {
//            @Override
//            public void done(String s, BmobException e) {
//                if(e==null){
//                    Log.i("123", "done: "+s);
//
//                    BmobUser user=new BmobUser();
//                    user.setUsername("散散");
//                    BmobRelation re=new BmobRelation();
//                    re.add(test);
//
//
//                    log.save(new SaveListener<String>() {
//                        @Override
//                        public void done(String s, BmobException e) {
//
//                            if(e==null){
//                                Log.i("123", "done: "+s);
//                            }   else {
//                                Log.i("dd", "done: "+e.getMessage());
//                            }
//
//                        }
//                    });
//                }   else {
//                    Log.i("dd", "done: "+e.getMessage());
//                }
//
//            }
//        });
//        Log.i("1233", "onCreate: "+test);


        //测试Httpget
        String url="http://bmob-cdn-8596.b0.upaiyun.com/2017/01/12/93ab565d40f2d14e80b812160aebeb96.json";

        //HttpGet<UIModel>get =new HttpGet<>();
      //  get.HttpRequestJsonTOModel(url,UIModel.class);






    }
}
