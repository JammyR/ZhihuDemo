package com.example.jammy.zhihudemo.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.jammy.zhihudemo.R;
import com.jude.rollviewpager.RollPagerView;

import java.util.List;


/**
 * Created by Jammy on 2016/6/15.
 */
public class ListViewAdapter extends BaseAdapter {

    Context context;
    List list;

    public ListViewAdapter(Context context) {
        this.context = context;
    }

    /**
     * 得到有多少个布局
     * @return
     */
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    /**
     * 得到当前位置应该使用什么布局
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (position == 0) {
            view = View.inflate(context,R.layout.viewpager,null);
            RollPagerView viewPager = (RollPagerView) view.findViewById(R.id.view_paper);
            viewPager.setAdapter(new ViewPaperAdapter());
        } else {
            view = View.inflate(context, R.layout.list_item, null);
        }
        return view;
    }

    /**
     * 用于设置内容的list
     * @param list
     */
    public void setList(List list){
        this.list = list;
    }


    /**
     * 用于复用的类
     */
    class ViewHolder{

    }

}
