package com.mydemo.ac;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.mydemo.R;
import com.ypk.library.interfac.OnTabClickListener;
import com.ypk.library.view.YPKTabLayoutView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ClassName HomeActivity
 * @Description TODO
 * @Author Administrator
 * @Date 2021-08-05 11:32
 */
public class HomeActivity extends AppCompatActivity {
    @BindView(R.id.mYPKTabLayoutView)
    YPKTabLayoutView mYPKTabLayoutView;
    private List<String> list = new ArrayList<>();
    private Fragment[] mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        initEvent();
        // openDialog();

    }

    private void initEvent() {
        mYPKTabLayoutView.addTabSelectedListener(new OnTabClickListener() {
            @Override
            public void tabSelectedListener(int i) {

            }
        });


    }


}
