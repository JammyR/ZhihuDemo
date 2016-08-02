package com.example.jammy.zhihudemo.Activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.example.jammy.zhihudemo.API.ZhihuDemo;
import com.example.jammy.zhihudemo.Adapter.ListViewAdapter;
import com.example.jammy.zhihudemo.Bean.StartImage;
import com.example.jammy.zhihudemo.Bean.StoryBase;
import com.example.jammy.zhihudemo.R;
import com.example.jammy.zhihudemo.Tools.NetUtil;
import com.example.jammy.zhihudemo.Tools.SPUtil;
import com.example.jammy.zhihudemo.Tools.ToastUtil;


import butterknife.Bind;
import okhttp3.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.SingleSubscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Jammy on 2016/6/15.
 * 存在的问题
 * 1.缓存机制
 * 2.请求失败的重新请求
 * 3.顶部菜单的制作
 * 4.抽屉菜单的制作(NavigationView)+（DrawerLayout）
 * 5.清除缓存按钮的制作
 * 6.图片加载问题使用Gradle
 */
public class MainActivity extends BaseActivity {
    String TAG = "MainActivity";
    @Bind(R.id.lv_main)
    ListView lvMain;
    @Bind(R.id.refresh)
    SwipeRefreshLayout refresh;

    ListViewAdapter listViewAdapter;
    SwipeRefreshLayoutListener swipeRefreshLayoutListener;

    @Override
    protected void initData() {
        listViewAdapter = new ListViewAdapter(this);
        swipeRefreshLayoutListener = new SwipeRefreshLayoutListener();
        refresh.setOnRefreshListener(swipeRefreshLayoutListener);
        refresh.setProgressViewOffset(false, 0, 30);
        refresh.setRefreshing(true);
        swipeRefreshLayoutListener.onRefresh();
        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, StoryActivity.class);
                intent.putExtra("id", String.valueOf(parent.getItemIdAtPosition(position)));
                startActivity(intent);
            }
        });

    }

    /**
     * 网络请求,这么做是为了代码让SwipeRefreshLayout刷新
     */
    class SwipeRefreshLayoutListener implements android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener {
        @Override
        public void onRefresh() {

            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(NetUtil.BASE_ADDRESS)
                    .build();
            ZhihuDemo zhihuDemo = retrofit.create(ZhihuDemo.class);

            zhihuDemo.getLastNews().subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleSubscriber<StoryBase>() {
                        @Override
                        public void onSuccess(StoryBase storyBase) {
                            listViewAdapter.setStoryList(storyBase.getStory());
                            listViewAdapter.setTopList(storyBase.getTop_story());
                            lvMain.setAdapter(listViewAdapter);
                            refresh.setRefreshing(false);
                        }

                        @Override
                        public void onError(Throwable error) {
                            ToastUtil.showToast(MainActivity.this, "网络连接失败，请重试");
                            refresh.setRefreshing(false);
                        }
                    });
        }
    }


    @Override
    protected int bindContentViewId() {
        return R.layout.activity_main;
    }

}
