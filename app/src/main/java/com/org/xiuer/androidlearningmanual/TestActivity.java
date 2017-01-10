package com.org.xiuer.androidlearningmanual;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.org.xiuer.androidlearningmanual.Adapter.AndroidTheory.Catalog;

import com.org.xiuer.androidlearningmanual.View.CompositeView;
import com.org.xiuer.androidlearningmanual.model.AndroidTheory.THomeModel;


import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

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

        THomeModel model =new THomeModel();
        model.setName("test");
        model.setId("6");
        List<Catalog>catalogses=new ArrayList<>();
        Catalog catalogs=new Catalog();
        catalogs.setName("123");
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


        BmobQuery query=new BmobQuery("THomeModel");

        query.findObjectsByTable(new QueryListener<JSONArray>() {
            @Override
            public void done(JSONArray jsonArray, BmobException e) {

                Log.i("dd", "done: "+jsonArray);
            }
        });



    }
}
