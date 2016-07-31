package com.example.jammy.zhihudemo.API;

import com.example.jammy.zhihudemo.Bean.StartImage;

import retrofit2.http.GET;
import rx.Observable;
import rx.Single;

/**
 * Created by Jammy on 2016/7/31.
 */
public interface ZhihuDemo {
    @GET("start-image/1080*1776")
    Single<StartImage> startInfo();

}
