package com.example.jammy.zhihudemo.Activity;


import android.graphics.Bitmap;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jammy.zhihudemo.API.ZhihuDemo;
import com.example.jammy.zhihudemo.Bean.StoryDetail;
import com.example.jammy.zhihudemo.R;
import com.example.jammy.zhihudemo.Tools.NetUtil;

import butterknife.Bind;
import okhttp3.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.SingleSubscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Jammy on 2016/7/17.
 * 存在问题：
 * 1.ToolBar的使用（CollapsingToolbarLayout可尝试）(Android-ObservableScrollView应该是这个)
 * 2.数据存储的问题，使用数据库？
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

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(NetUtil.BASE_ADDRESS)
                .build();
        ZhihuDemo zhihuDemo = retrofit.create(ZhihuDemo.class);

        zhihuDemo.getStoryDetail(id).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleSubscriber<StoryDetail>() {

                    @Override
                    public void onSuccess(StoryDetail storyDetail) {
                        webView.loadData(("<head>\n" +
                                "    <link rel=\"stylesheet\" style=\"text/css\" href=\"") + storyDetail.getCss().get(0) + "\"/>\n" +
                                "</head>" + storyDetail.getBody(), "text/html; charset=UTF-8", null);
                        tvDetail.setText(storyDetail.getTitle());
                        Glide.with(StoryActivity.this).load(storyDetail.getImage()).fitCenter().into(ivDetail);
                    }

                    @Override
                    public void onError(Throwable error) {

                    }
                });

    }

    @Override
    protected int bindContentViewId() {
        return R.layout.activity_story;
    }
}
