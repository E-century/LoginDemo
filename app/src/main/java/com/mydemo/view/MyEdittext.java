package com.mydemo.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;

import com.mydemo.R;
import com.mydemo.utils.ResourceUtil;

/**
 * @ClassName MyEdittext
 * @Description TODO
 * @Author Administrator
 * @Date
 */
public class MyEdittext  extends androidx.appcompat.widget.AppCompatEditText implements View.OnFocusChangeListener{

    private Context context;


    public void setOnCheckInputListener(OnCheckInputListener onCheckInputListener) {
        this.onCheckInputListener = onCheckInputListener;
    }

    private OnCheckInputListener onCheckInputListener;
    private GradientDrawable  drawable;

    /**
     * 检测输入是否符合要求的回调
     */
    public interface OnCheckInputListener {
        /**
         * 检测输入的方法
         *
         * @param v   点击的view
         * @param str 输入的字符串
         * @return 检测成功返回true, 检测失败返回false
         */
        boolean checkInput(View v, String str);
    }

    public MyEdittext(Context context) {
        this(context, null);
    }

    public MyEdittext(Context context, AttributeSet attrs) {

        super(context, attrs);
        this.context = context;
  //   LayerDrawable layerDrawable = (LayerDrawable) getBackground();
        LayerDrawable layerDrawable = (LayerDrawable)getBackground();
        drawable = (GradientDrawable) layerDrawable.findDrawableByLayerId(R.id.shape);
        setOnFocusChangeListener(this);
    }



    private LayerDrawable getLayerDrawable(int foregroundColor){

        int radius0 = 10;
        float[] outerR = new float[] { radius0, radius0, radius0, radius0, radius0, radius0, radius0, radius0 };
        RoundRectShape roundRectShape0 = new RoundRectShape(outerR, null, null);

        int radius1 = 10;
        float[] outerR1 = new float[] { radius1, radius1, radius1, radius1, radius1, radius1, radius1, radius1 };
        RoundRectShape roundRectShape1 = new RoundRectShape(outerR1, null, null);

        ShapeDrawable shapeDrawableBg = new ShapeDrawable();
        shapeDrawableBg.setPadding(0, 0, 0, 0);
        shapeDrawableBg.setShape(roundRectShape0);
        shapeDrawableBg.getPaint().setStyle(Paint.Style.FILL);
        shapeDrawableBg.getPaint().setColor(0xffbbbbbb);

        ShapeDrawable shapeDrawableFg = new ShapeDrawable();
        shapeDrawableFg.setPadding(23, 23, 23, 23);
        shapeDrawableFg.setShape(roundRectShape1);
        shapeDrawableFg.getPaint().setStyle(Paint.Style.FILL);
        shapeDrawableFg.getPaint().setColor(foregroundColor);

        Drawable[] layers = {shapeDrawableBg, shapeDrawableFg};
        LayerDrawable layerDrawable = new LayerDrawable(layers);
        layerDrawable.setLayerInset(1, 0, 0, 1, 1);

        return layerDrawable;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (drawable == null){
            System.out.println("drawable-" + drawable);
            return;
        }
      if (hasFocus) {
            drawable.setStroke((int) ResourceUtil.getDimens(R.dimen.line), ResourceUtil.getColor(R.color.color_d1d1d1));
        } else {

            if (onCheckInputListener != null && onCheckInputListener.checkInput(this, getText().toString().trim())) {
                drawable.setStroke((int) ResourceUtil.getDimens(R.dimen.line), ResourceUtil.getColor(R.color.color_f1f1f1));

            } else if (onCheckInputListener == null) {
                drawable.setStroke((int) ResourceUtil.getDimens(R.dimen.line), ResourceUtil.getColor(R.color.color_f1f1f1));
            } else {
                drawable.setStroke((int) ResourceUtil.getDimens(R.dimen.line), ResourceUtil.getColor(R.color.color_ff6f00));

            }
        }
    }
}
