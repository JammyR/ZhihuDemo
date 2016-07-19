package com.example.jammy.zhihudemo.Activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.jammy.zhihudemo.Adapter.ListViewAdapter;
import com.example.jammy.zhihudemo.Bean.StoryBase;
import com.example.jammy.zhihudemo.CallBack.ResultCallback;
import com.example.jammy.zhihudemo.R;
import com.example.jammy.zhihudemo.Tools.NetUtil;
import com.example.jammy.zhihudemo.Tools.ToastUtil;

import java.util.ArrayList;

import butterknife.Bind;
import okhttp3.Call;

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

    @Override
    protected void initData() {
        listViewAdapter = new ListViewAdapter(this);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                NetUtil.getInstance().getLatestNews(new ResultCallback<StoryBase>() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtil.showToast(MainActivity.this, "网络失败");
                    }

                    @Override
                    public void onResponse(StoryBase response, int id) {
                        Log.v(TAG,response.getDate());

                        Log.v("返回的长度：", String.valueOf(response.getStory().size()));

                        listViewAdapter.setStoryList(response.getStory());
                        listViewAdapter.setTopList(response.getTop_story());

                        lvMain.setAdapter(listViewAdapter);
                        refresh.setRefreshing(false);
                    }
                });
            }
        });

        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,StoryActivity.class);
                intent.putExtra("id",String.valueOf(parent.getItemIdAtPosition(position)));
                startActivity(intent);
            }
        });

    }

    @Override
    protected int bindContentViewId() {
        return R.layout.activity_main;
    }

}
