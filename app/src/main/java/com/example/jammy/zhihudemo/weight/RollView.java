package com.example.jammy.zhihudemo.weight;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.jude.rollviewpager.RollPagerView;

/**
 * Created by Jammy on 2016/7/20.
 */
public class RollView extends RollPagerView{

    int x;
    int y;

    public RollView(Context context) {
        super(context);
    }

    public RollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        int xx = (int) ev.getX();
//
//
//        switch (ev.getAction()){
//            case MotionEvent.ACTION_DOWN:
//                x = xx;
//                Log.v("点击事件触发了：","11111111");
//                break;
//
//            case MotionEvent.ACTION_MOVE:
//                if(Math.abs(x-xx)>0)
//                    return true;
//                break;
//        }
//        return super.dispatchTouchEvent(ev);
//    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int xx = (int) ev.getX();
        int yy = (int) ev.getY();

        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                x = xx;
                y = yy;
                break;

            case MotionEvent.ACTION_MOVE:
                if(Math.abs(x-xx)<=20 && yy-y>10)
                    getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}
