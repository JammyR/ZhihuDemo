package com.example.jammy.zhihudemo.Adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.jammy.zhihudemo.R;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;

import java.util.List;

/**
 * Created by Jammy on 2016/6/15.
 */
public class ViewPaperAdapter extends StaticPagerAdapter {
    //将这个设置成可以动态设置的图片和文字的封装

    /**
     * 到时根据返回值的情况来设置这个list的类型
     */
    List list;



    /**
     * 返回位置第N个位置的View，这个View要做成自定义View，包括图片和文字
     * @param container
     * @param position
     * @return
     */
    @Override
    public View getView(ViewGroup container, int position) {
        ImageView iv = new ImageView(container.getContext());
//        iv.setImageResource(pic[position]);
        return iv;
    }

    /**
     * 返回ViewPager中的个数
     * @return
     */
    @Override
    public int getCount() {
        return list.size();
    }

    /**
     * 用于设置list的值，图片根据List来确定
     * @param list
     */
    public void setList(List list){
        this.list = list;
    }
}
