package com.example.jammy.zhihudemo.Activity;


import android.graphics.Bitmap;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jammy.zhihudemo.Bean.StoryDetail;
import com.example.jammy.zhihudemo.CallBack.ResultCallback;
import com.example.jammy.zhihudemo.R;
import com.example.jammy.zhihudemo.Tools.NetUtil;

import butterknife.Bind;
import okhttp3.Call;

/**
 * Created by Jammy on 2016/7/17.
 * 存在问题：
 * 1.ToolBar的使用（CollapsingToolbarLayout可尝试）(Android-ObservableScrollView应该是这个)
 * 2.数据存储的问题，使用数据库？
 *
 *
 */
public class StoryActivity extends BaseActivity {


    WebSettings webSettings;
    @Bind(R.id.webView)
    WebView webView;
    @Bind(R.id.iv_detail)
    ImageView ivDetail;
    @Bind(R.id.tv_detail)
    TextView tvDetail;


    @Override
    protected void initData() {
        webSettings = webView.getSettings();
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        String id = getIntent().getStringExtra("id");



        NetUtil.getInstance().getDetail(id, new ResultCallback<StoryDetail>() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(StoryDetail response, int id) {
                webView.loadData(("<head>\n" +
                        "    <link rel=\"stylesheet\" style=\"text/css\" href=\"") + response.getCss().get(0) + "\"/>\n" +
                        "</head>" + response.getBody(), "text/html; charset=UTF-8", null);
                tvDetail.setText(response.getTitle());
                Glide.with(StoryActivity.this).load(response.getImage()).fitCenter().into(ivDetail);
            }
        });
    }

    @Override
    protected int bindContentViewId() {
        return R.layout.activity_story;
    }
}
