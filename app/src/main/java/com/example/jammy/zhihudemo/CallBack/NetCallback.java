package com.example.jammy.zhihudemo.CallBack;

import android.util.Log;

import com.example.jammy.zhihudemo.Bean.Result;
import com.example.jammy.zhihudemo.Bean.StartImg;
import com.example.jammy.zhihudemo.Tools.LogUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;


import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Jammy on 2016/6/19.
 */
public abstract class NetCallback<T> extends Callback<T> {

    Class clazz ;

    public NetCallback(Class clazz){
        this.clazz = clazz;
    }


    //这里解析得到的数据
    @Override
    public T parseNetworkResponse(Response response, int id) throws Exception {
        String str = response.body().string();
        Gson gson = new Gson();
        LogUtil.showLog("开始解析,",str);
        T t = (T) gson.fromJson(str,clazz);
        return t;
    }
}