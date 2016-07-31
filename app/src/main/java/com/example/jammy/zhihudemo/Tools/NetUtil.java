package com.example.jammy.zhihudemo.Tools;


/**
 * Created by Jammy on 2016/6/19.
 */
public class NetUtil {

    public final static String BASE_ADDRESS = "http://news-at.zhihu.com/api/4/";
    final String START_IMAGE = BASE_ADDRESS + "start-image/1080*1776";    //后面的数字接收如下分辨率的，可自行更改：320*432，480*728，720*1184，1080*1776
    final String VERSION_CHECK = BASE_ADDRESS + "version/android/2.3.0";  //后面的数字表示当前的版本号
    final String LATEST_STORY = BASE_ADDRESS + "news/latest";
    final String STORY_DETAIL = BASE_ADDRESS + "news/";
}
