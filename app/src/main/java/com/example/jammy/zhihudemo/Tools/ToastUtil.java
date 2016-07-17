package com.example.jammy.zhihudemo.Tools;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Jammy on 2016/6/19.
 */
public class ToastUtil {

    public static void showToast(Context context , String msg){
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }

}
