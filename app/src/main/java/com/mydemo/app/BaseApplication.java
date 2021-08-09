package com.mydemo.app;

import android.app.Application;
import android.widget.Toast;
public class BaseApplication extends Application {
    private static String PREF_NAME = "mydemo.pref";
    private static BaseApplication myApplication;
    private static Toast toast;
    private static BaseApplication mContext;

    public static BaseApplication getInstance() {
        return myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        mContext = this;
        toast =Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
    }

    public static void showToast(String content, int time) {
        toast.setText(content);
        toast.setDuration(time);
        toast.show();
    }

    public static Application getContext() {
        return mContext;
    }
}
