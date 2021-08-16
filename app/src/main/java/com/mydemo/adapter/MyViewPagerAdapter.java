package com.mydemo.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @ClassName MyViewPagerAdapter
 * @Description TODO
 * @Author Administrator
 * @Date 2021-08-12 9:57
 */
public class MyViewPagerAdapter  extends FragmentPagerAdapter {

    private int mChildCount = 0;
    private Context context;
    private List<Fragment> fragments;
    private String[] titles;
    private Fragment[] fragments2;

    public MyViewPagerAdapter(FragmentManager fm, String[] titles, List<Fragment> fragments) {
        super(fm);
        this.titles = titles;
        this.fragments = fragments;
    }

    public MyViewPagerAdapter(FragmentManager fm, String[] titles,
                              List<Fragment> fragments,
                              FinishUpdateListener mFinishUpdateListener) {
        super(fm);
        this.titles = titles;
        this.fragments = fragments;
        this.mFinishUpdateListener = mFinishUpdateListener;
    }



    @Override
    public Fragment getItem(int arg0) {
        return fragments.get(arg0);
        //return fragments2[arg0];
    }

    @Override
    public void notifyDataSetChanged() {
        mChildCount = getCount();
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        //重绘
        if (mChildCount > 0) {
            mChildCount--;
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        //System.out.println("size:::"+fragments.size());
        return fragments.size();
        //return fragments2.length;
    }
    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        super.finishUpdate(container);
        if (mFinishUpdateListener!=null){
            mFinishUpdateListener.onToolBarChange();
        }
        //System.out.println("调用了finishUpdate...............");
    }
    public void setFinishUpdateListener(FinishUpdateListener finishUpdateListener) {
        mFinishUpdateListener = finishUpdateListener;
    }
    FinishUpdateListener mFinishUpdateListener;
    public interface  FinishUpdateListener{
        void onToolBarChange();
    }

}
