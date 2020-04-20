package com.example.parttime.utils;

import android.widget.Toast;


import com.example.parttime.AppApplication;

import androidx.annotation.NonNull;


public class ToastUtil {

    private static Toast mToast;

    /**
     * 显示短时间的Toast
     * @param text
     */
    public static void showShort(@NonNull CharSequence text){
        show(text, Toast.LENGTH_SHORT);
    }

    /**
     * 显示长时间的Toast
     * @param text
     */
    public static void showLong(@NonNull CharSequence text){
        show(text, Toast.LENGTH_LONG);
    }

    private static void show(@NonNull CharSequence text, @NonNull int duration){
        if (mToast != null){
            mToast.cancel();
        }
        mToast = Toast.makeText(AppApplication.getInstance().getApplicationContext(), text, duration);
        mToast.show();
    }
}
