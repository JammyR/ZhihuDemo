package com.example.jammy.zhihudemo.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jammy.zhihudemo.Bean.TopStory;
import com.example.jammy.zhihudemo.R;
import com.example.jammy.zhihudemo.Tools.NetUtil;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.zhy.http.okhttp.callback.BitmapCallback;

import java.util.List;

import okhttp3.Call;

/**
 * Created by Jammy on 2016/6/15.
 */
public class ViewPaperAdapter extends StaticPagerAdapter {
    //将这个设置成可以动态设置的图片和文字的封装

    /**
     * 到时根据返回值的情况来设置这个list的类型
     */
    List<TopStory> list;
    Context context;

    public ViewPaperAdapter(Context context) {
        this.context = context;
    }

    /**
     * 返回位置第N个位置的View，这个View要做成自定义View，包括图片和文字
     *
     * @param container
     * @param position
     * @return
     */
    @Override
    public View getView(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.top_item, null);
        ImageView iv = (ImageView) view.findViewById(R.id.iv_top);
        TextView tv = (TextView) view.findViewById(R.id.tv_top);
        tv.setText(list.get(position).getTitle());
        Glide.with(context).load(list.get(position).getImage()).fitCenter().into(iv);
        return view;
    }

    /**
     * 返回ViewPager中的个数
     *
     * @return
     */
    @Override
    public int getCount() {
        return list.size();
    }

    /**
     * 用于设置list的值，图片根据List来确定
     *
     * @param list
     */
    public void setList(List list) {
        this.list = list;
    }


}
