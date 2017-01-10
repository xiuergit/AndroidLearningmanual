package com.org.xiuer.androidlearningmanual;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.IOException;
import java.io.InputStream;

import  cn.bmob.v3.Bmob;
@ContentView(R.layout.activity_main)
public class BaseActivity extends AppCompatActivity {

    private  static String TAG="BaseActivity";



    @ViewInject(R.id.tv_content)
     public  TextView mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initStatus();
        x.view().inject(this);
        Bmob.initialize(this,"60b4b062bd09c5013e4aa7b1d21af05c");

    }


    /***
     * 状态栏的设置
     */
    public void initStatus(){
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.KITKAT){
            Window window=getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor( getResources().getColor(R.color.colorLilac)  );
        }

    }

    /***
     *  根据 名字 读取asserts 下的文件
     * @param name 例如  "asserts.txt"
     * @return
     */
    public  String readStringFromAssert(String name) {
        InputStream inputStream = null;
        String text="";
        try {
            inputStream = getAssets().open(name);
            int size = inputStream.available();
            byte buffer[] = new byte[size];
            inputStream.read(buffer);
            text = new String(buffer);

        } catch (Exception e) {

            Log.i(TAG, "readStringFromAssert: " + e.getMessage());

        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }

        return text;
    }




}
