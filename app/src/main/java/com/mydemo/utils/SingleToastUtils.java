package com.mydemo.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * @ClassName SingleToastUtils
 * @Description TODO
 * @Author Administrator
 * @Date 2021-08-06 11:27
 */
class SingleToastUtils {
    private static Toast toast;
    public static void getSingleToast(Context context, String text) {
        if (toast == null) {
            //创建一个空的吐司
            toast = Toast.makeText(context, "", Toast.LENGTH_LONG);
        }
        // 给吐司的内容设置自己想要的值
        toast.setText(text.toString());
        toast.show();

    }
}
