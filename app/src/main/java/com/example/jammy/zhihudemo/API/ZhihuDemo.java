package com.example.jammy.zhihudemo.API;

import com.example.jammy.zhihudemo.Bean.StartImage;
import com.example.jammy.zhihudemo.Bean.StoryBase;
import com.example.jammy.zhihudemo.Bean.StoryDetail;

import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import rx.Observable;
import rx.Single;

/**
 * Created by Jammy on 2016/7/31.
 */
public interface ZhihuDemo {
    @GET("start-image/1080*1776")
    Single<StartImage> startInfo();

    @GET("news/latest")
    Single<StoryBase> getLastNews();

    @GET("news/{id}")
    Single<StoryDetail> getStoryDetail(@Path("id") String id);
}
