package com.example.jammy.zhihudemo.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jammy.zhihudemo.Bean.Story;
import com.example.jammy.zhihudemo.Bean.TopStory;
import com.example.jammy.zhihudemo.R;
import com.example.jammy.zhihudemo.Tools.NetUtil;
import com.jude.rollviewpager.RollPagerView;
import com.zhy.http.okhttp.callback.BitmapCallback;

import java.util.List;

import okhttp3.Call;


/**
 * Created by Jammy on 2016/6/15.
 */
public class ListViewAdapter extends BaseAdapter {

    Context context;
    List<Story> list_story;
    List<TopStory> list_top;

    public ListViewAdapter(Context context) {
        this.context = context;
    }

    /**
     * 得到有多少个布局
     *
     * @return
     */
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    /**
     * 得到当前位置应该使用什么布局
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (position == 0) return 0;
        else return 1;
    }

    @Override
    public int getCount() {
        return list_story.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return list_story.get(position - 1).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (getItemViewType(position) == 0) {
            //TODO:做ViewPager
            view = LayoutInflater.from(context).inflate(R.layout.viewpager, null);
            RollPagerView viewPager = (RollPagerView) view.findViewById(R.id.view_paper);
            viewPager.setPlayDelay(2000);
            ViewPaperAdapter adapter = new ViewPaperAdapter(context);
            adapter.setList(list_top);
            viewPager.setAdapter(adapter);

        } else {
            view = LayoutInflater.from(context).inflate(R.layout.list_item, null);
            TextView tv = (TextView) view.findViewById(R.id.tv_describes);
            ImageView iv = (ImageView) view.findViewById(R.id.iv_icon);
            tv.setText(list_story.get(position - 1).getTitle());
            Glide.with(context).load(list_story.get(position - 1).getImages().get(0)).fitCenter().into(iv);
        }
        return view;
    }

    /**
     * 用于设置内容的list
     *
     * @param list
     */
    public void setStoryList(List list) {
        list_story = list;
    }

    /**
     * 设置顶部ViewPage的List
     *
     * @param list
     */
    public void setTopList(List list) {
        list_top = list;
    }

}
