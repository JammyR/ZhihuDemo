package com.example.jammy.zhihudemo.Tools;

import android.util.Log;

import com.example.jammy.zhihudemo.Bean.StartImg;
import com.example.jammy.zhihudemo.CallBack.NetCallback;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by Jammy on 2016/6/19.
 */
public class NetUtil {

    final String BASE_ADDRESS = "http://news-at.zhihu.com/api/4/";
    final String START_IMAGE = BASE_ADDRESS + "start-image/1080*1776";    //后面的数字接收如下分辨率的，可自行更改：320*432，480*728，720*1184，1080*1776
    final String VERSION_CHECK = BASE_ADDRESS + "version/android/2.3.0";  //后面的数字表示当前的版本号

    private static NetUtil netUtil;


    public static NetUtil getInstance(){
        if(netUtil==null)netUtil = new NetUtil();
        return netUtil;
    }


    /**
     * 得到开始图片
     * @param callback
     */
    public void getStartImage(Callback callback){
        //不支持自动解析，使用JSON解析
//        OkHttpUtils.get().url(START_IMAGE).build().execute(new NetCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//
//            }
//
//            @Override
//            public void onResponse(StartImg response, int id) {
//                LogUtil.showLog("LoadImageText",response.getText());
//                LogUtil.showLog("LoadImage:",response.getImg());
//            }
//        });
        OkHttpUtils.get().url(START_IMAGE).build().execute(callback);
    }

}
