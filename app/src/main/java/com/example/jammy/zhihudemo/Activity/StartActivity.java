package com.example.jammy.zhihudemo.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jammy.zhihudemo.Bean.StartImage;
import com.example.jammy.zhihudemo.CallBack.ResultCallback;
import com.example.jammy.zhihudemo.R;
import com.example.jammy.zhihudemo.Tools.NetUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;

import butterknife.Bind;
import okhttp3.Call;

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

        new CheckImage().execute();

    }

    @Override
    protected int bindContentViewId() {
        return R.layout.activity_start;
    }


    class CheckImage extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] params) {
            NetUtil.getInstance().getStartImage(new ResultCallback<StartImage>() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    Log.v(TAG,"获取封面信息失败");
                }

                @Override
                public void onResponse(final StartImage response, int id) {
                    tv_author.setText(response.getText());
                    Glide.with(StartActivity.this).load(response.getImg()).fitCenter().into(iv_cover);
                }
            });
            return null;
        }
    }
}
