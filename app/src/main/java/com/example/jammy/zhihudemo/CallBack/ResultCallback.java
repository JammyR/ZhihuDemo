package com.example.jammy.zhihudemo.CallBack;

import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.Callback;

import java.lang.reflect.ParameterizedType;

import okhttp3.Response;

/**
 * Created by Jammy on 2016/7/16.
 */
public abstract class ResultCallback<T> extends Callback<T> {

    @Override
    public T parseNetworkResponse(Response response, int id) throws Exception {
        Gson gson = new Gson();
        Class<T> entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        T t = gson.fromJson(response.body().string(), entityClass);
        return t;
    }

}
