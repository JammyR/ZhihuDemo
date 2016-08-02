package com.example.jammy.zhihudemo.Activity;

import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jammy.zhihudemo.API.ZhihuDemo;
import com.example.jammy.zhihudemo.Bean.StartImage;
import com.example.jammy.zhihudemo.R;
import com.example.jammy.zhihudemo.Tools.NetUtil;
import com.example.jammy.zhihudemo.Tools.SPUtil;

import butterknife.Bind;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.SingleSubscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Jammy on 2016/7/9.
 * 存在问题：
 * 1.下载图片后的缓存
 * 2.设置初始化图片
 */
public class StartActivity extends BaseActivity {

    String TAG = "StartActivity";

    @Bind(R.id.iv_cover)
    ImageView iv_cover;
    @Bind(R.id.tv_author)
    TextView tv_author;

    Animation animation;

    @Override
    protected void initData() {

        String author = (String) SPUtil.getData(this,"startImage","text","");
        String image_url = (String) SPUtil.getData(this,"startImage","img","");
        if(author.equals("")&&author.equals("")){

        }else{
            tv_author.setText(author);
            Glide.with(this).load(image_url).fitCenter().into(iv_cover);
        }

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(NetUtil.BASE_ADDRESS)
                .build();
        ZhihuDemo zhihuDemo = retrofit.create(ZhihuDemo.class);

        zhihuDemo.startInfo().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleSubscriber<StartImage>() {

                    @Override
                    public void onSuccess(StartImage startImage) {
                        tv_author.setText(startImage.getText());
                        Glide.with(StartActivity.this).load(startImage.getImg()).fitCenter().into(iv_cover);
                        SPUtil.saveData(StartActivity.this,"startImage",startImage);
                    }

                    @Override
                    public void onError(Throwable error) {

                    }
                });

        animation = AnimationUtils.loadAnimation(this, R.anim.anim_start_page);
        animation.setFillAfter(true);//设置为true  那么在结束时停留在结束阶段
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        iv_cover.setAnimation(animation);
    }

    @Override
    protected int bindContentViewId() {
        return R.layout.activity_start;
    }

}
