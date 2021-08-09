package com.mydemo.utils;

import android.graphics.drawable.Drawable;

import com.mydemo.app.BaseApplication;


public class ResourceUtil {
    public static String getString(int id) {
        return BaseApplication.getContext().getString(id);
    }

   public static Drawable getDrawable(int id) {
        return BaseApplication.getContext().getResources().getDrawable(id);
    }


    public static int getColor(int id) {
        return BaseApplication.getContext().getResources().getColor(id);
    }

    public static float getDimens(int id) {
        return BaseApplication.getContext().getResources().getDimension(id);
    }
}
