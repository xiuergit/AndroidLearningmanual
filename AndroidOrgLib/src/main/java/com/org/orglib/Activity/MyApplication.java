package com.org.orglib.Activity;

import android.app.Activity;
import android.app.Application;

import org.xutils.x;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by xiuer on 16/8/17.
 */
public class MyApplication  extends Application{

    private final String TAG = "MyApplication";


    private List<Activity> mList = new LinkedList<Activity>();

    @Override
    public void onCreate() {
        super.onCreate();
        //依赖注入初始化
        x.Ext.init(this);

     }



    public void addActivity(Activity activity) {
        mList.add(activity);
    }

    public void exit() {
        try {
            for (Activity activity : mList) {
                if (activity != null) {
                    activity.finish();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }


}
