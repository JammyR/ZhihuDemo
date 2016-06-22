package com.example.jammy.zhihudemo.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.ListView;

import com.example.jammy.zhihudemo.Adapter.ListViewAdapter;
import com.example.jammy.zhihudemo.Bean.StartImg;
import com.example.jammy.zhihudemo.CallBack.NetCallback;
import com.example.jammy.zhihudemo.R;
import com.example.jammy.zhihudemo.Tools.LogUtil;
import com.example.jammy.zhihudemo.Tools.NetUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Jammy on 2016/6/15.
 */
public class MainActivity extends Activity {
    @Bind(R.id.lv_main)
    ListView lvMain;
    @Bind(R.id.refresh)
    SwipeRefreshLayout refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);
        ListViewAdapter adapter = new ListViewAdapter(this);
        adapter.setList(new ArrayList());
        lvMain.setAdapter(adapter);

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LogUtil.showLog("刷新了","123");
                NetUtil.getInstance().getStartImage(new NetCallback<StartImg>(StartImg.class) {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(StartImg response, int id) {
                        LogUtil.showLog("作者名字：",response.getText());
                        LogUtil.showLog("图片地址：",response.getImg());


                    }

                });

            }
        });
    }
}
