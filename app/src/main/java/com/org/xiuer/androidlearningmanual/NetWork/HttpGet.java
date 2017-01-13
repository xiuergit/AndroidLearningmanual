package com.org.xiuer.androidlearningmanual.NetWork;

import android.util.Log;

import com.google.gson.Gson;

import org.apache.http.protocol.HTTP;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zhangxiu on 2017/1/13.
 */

public class HttpGet <E>{
    private static  OkHttpClient  client=new OkHttpClient();

    public HttpGet() {

    }

    /**
     * get 请求
     * @param url
     * @param callback
     */
    public     void  httpRequestGet(String url, final HttpCallback callback){
        Request request=new Request.Builder().url(url).build();
       client.newCall(request).enqueue(new Callback() {
           @Override
           public void onFailure(Call call, IOException e) {
                 callback.onFailure(e,call.request());
           }
           @Override
           public void onResponse(Call call, Response response) throws IOException {
               callback.onSuccess(response,call.request());
           }
       });
    }

    /***
     *  json to model
     * @param url
     * @param eClass  model 类
     * @param callback 返回 model 对象
     */
    public void  HttpRequestJsonTOModel(String url, final Class<E> eClass, final ResultCallback<E> callback){
        httpRequestGet(url, new HttpCallback() {
            @Override
            public void onSuccess(Response response, Request request) {
                Gson gson=new Gson();
                Object object= gson.fromJson(response.body().charStream(),eClass);
                callback.OnSuccess(object);
            }

            @Override
            public void onFailure(IOException e, Request request) {
                callback.OnFailure(e);
            }
        });
    }

    public interface  ResultCallback<E>{

        void OnSuccess(Object o);
        void OnFailure(IOException e);
    }

    public  interface  HttpCallback{
        void  onSuccess(Response response,Request request);
        void  onFailure(IOException e,Request request);
    }

}
