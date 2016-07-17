package com.example.jammy.zhihudemo.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import butterknife.ButterKnife;

/**
 * Created by Jammy on 2016/7/9.
 */
public abstract class BaseActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindContentViewId());
        ButterKnife.bind(this);
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    /**
     * 用于View绑定操作
     */
    protected abstract void initData();

    /**
     * 设置布局文件
     */
    protected abstract int bindContentViewId();

}
