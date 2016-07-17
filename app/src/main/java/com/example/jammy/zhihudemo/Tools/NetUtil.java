package com.example.jammy.zhihudemo.Tools;


import com.example.jammy.zhihudemo.CallBack.ResultCallback;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Jammy on 2016/6/19.
 */
public class NetUtil {

    final String BASE_ADDRESS = "http://news-at.zhihu.com/api/4/";
    final String START_IMAGE = BASE_ADDRESS + "start-image/1080*1776";    //后面的数字接收如下分辨率的，可自行更改：320*432，480*728，720*1184，1080*1776
    final String VERSION_CHECK = BASE_ADDRESS + "version/android/2.3.0";  //后面的数字表示当前的版本号
    final String LATEST_STORY = BASE_ADDRESS + "news/latest";
    final String STORY_DETAIL = BASE_ADDRESS + "news/";

    private static NetUtil netUtil;


    public static NetUtil getInstance() {
        if (netUtil == null) netUtil = new NetUtil();
        return netUtil;
    }

    /**
     * 获取封面文字图片
     *
     * @param callback
     */
    public void getStartImage(ResultCallback callback) {
        OkHttpUtils.get().url(START_IMAGE).build().execute(callback);
    }

    /**
     * 专门用于加载图片请求
     *
     * @param url
     * @param callback
     */
    public void getImage(String url, BitmapCallback callback) {
        OkHttpUtils.get().url(url).build().execute(callback);
    }

    /**
     * 用于获取最新信息
     * @param callback
     */
    public void getLatestNews(ResultCallback callback) {
        OkHttpUtils.get().url(LATEST_STORY).build().execute(callback);
    }

    public void getDetail(String id,ResultCallback callback){
        OkHttpUtils.get().url(STORY_DETAIL+id).build().execute(callback);
    }
}
