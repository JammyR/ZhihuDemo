package com.example.jammy.zhihudemo.Activity;


import android.util.Log;
import android.webkit.WebView;

import com.example.jammy.zhihudemo.Bean.StoryDetail;
import com.example.jammy.zhihudemo.CallBack.ResultCallback;
import com.example.jammy.zhihudemo.R;
import com.example.jammy.zhihudemo.Tools.NetUtil;

import butterknife.Bind;
import okhttp3.Call;

/**
 * Created by Jammy on 2016/7/17.
 */
public class StoryActivity extends BaseActivity {

    @Bind(R.id.webView)
    WebView webView;

    @Override
    protected void initData() {
        String id = getIntent().getStringExtra("id");
        NetUtil.getInstance().getDetail(id, new ResultCallback<StoryDetail>() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(StoryDetail response, int id) {
                Log.v("反悔了","反悔了");
            }
        });
    }

    @Override
    protected int bindContentViewId() {
        return R.layout.activity_story;
    }


}
