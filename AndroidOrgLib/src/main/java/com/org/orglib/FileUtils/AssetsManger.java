package com.org.orglib.FileUtils;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.internal.Primitives;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by xiuer on 16/11/14.
 */

public class AssetsManger {

    /**
     * 从json文件中 读取 数据  返回一个对象
     * @param assertName   文件名
     * @param mClass
     * @param mContext     当前上下文
     * @param <T>
     * @return
     * @throws IOException
     */
    public static  <T> T readAssertToObject(String assertName, Class<T>mClass , Context mContext)  {

        InputStream is;
        BufferedReader reader;
        Object object=new Object();
        try {
            is=mContext.getAssets().open(assertName);
            reader=new BufferedReader(new InputStreamReader(is));
            Gson gson=new Gson();
            object = gson.fromJson(reader,mClass);


        } catch (IOException e) {
            e.printStackTrace();
        }

        return  Primitives.wrap(mClass).cast(object);
    }

}
