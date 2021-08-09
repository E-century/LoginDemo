package com.mydemo.ac;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mydemo.R;
import com.mydemo.app.BaseApplication;
import com.mydemo.utils.FormatUtil;
import com.mydemo.view.MyEdittext;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.pw_im)
    ImageView pwIm;

    @BindView(R.id.login_button)
    Button loginButton;
    @BindView(R.id.error_tv)
    TextView errorTv;
    @BindView(R.id.check_container)
    LinearLayout checkContainer;
    @BindView(R.id.weibo_im)
    ImageView weiboIm;
    @BindView(R.id.weixin_im)
    ImageView weixinIm;
    @BindView(R.id.qq_im)
    ImageView qqIm;
    @BindView(R.id.login_username)
    EditText loginUsername;
    @BindView(R.id.login_password)
    EditText loginPassword;


    /*
        *     1.1 页面元素：实现图上ui(去除记住密码，忘记密码，没有账号？马上注册等附加ui以及功能)
        2.2 页面校验：
       1.2.1 页面的背景半透明灰色，登陆框背景白色居中(去除记住密码，忘记密码，没有账号？马上注册等附加功能以及ui)。
       1.2.2 登陆按钮背景默认是置灰不可点击，当用户名和密码客户端检验通过再背景变为蓝色，文字为白色，如图上所视
       1.2.3 用户名的默认字符(请输入英文/数字),密码的默认字符(请输入密码)
       1.2.4 用户名的检验，用户名输入框失去焦点后，首先判断输入框是否有输入文字，如果未输入文字则不处理，如果有输入文字，
       *则判断，用户名长度是否小于等于20个字符(英文，数字算一个字符），如果大于20个字符则输入框底线标红，文字变红，用户名框右侧则有文字提示文字过长，校验正常则正常显示
       1.2.5 密码的校验，密码框的文本要求是必须包含英文字母，数字，特殊符号，当密码框失去焦点后，则检验密码是否符合以上规则，如果不符合则下划线标红，右侧有提醒输入英文字母，数字，特殊符合组合
          1.2.6 用2个标识分别来标识用户名，密码符合要求，当符合要求时，登陆按钮置为可以点击，背景变成蓝色
        *
        *
        *
        * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initEvent();
    }

    private void initEvent() {
        loginUsername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    //失去焦点后
                    checkLogin();
                }
            }
        });

        loginUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                checkLogin();
            }
        });


        loginPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                //校验密码
                checkPassword();
            }
        });


    }

    //设置底部按钮的背景颜色
    private void setBtBackground(boolean flag) {
        if (flag) {
            //改变按钮背景
            if (usernameFlag && passwordFlag) {
                loginButton.setBackgroundResource(R.drawable.login_select_bg);
                loginButton.setSelected(true);
            } else {
                loginButton.setBackgroundResource(R.drawable.login_nomore_bg);
                loginButton.setSelected(false);
            }
        } else {
            loginButton.setBackgroundResource(R.drawable.login_nomore_bg);
            loginButton.setSelected(false);
        }
    }

    private boolean usernameFlag;

    //检测登录
    private void checkLogin() {
        String username = loginUsername.getText().toString().trim();
        if (!TextUtils.isEmpty(username)) {
            if (username.length() > 20) {
                errorTv.setVisibility(View.VISIBLE);
                errorTv.setText("用户名过长,请重新输入");
                usernameFlag = false;
                setBtBackground(false);
                loginUsername.setBackgroundResource(R.drawable.line_error_shape);
            } else {

                usernameFlag = true;
                errorTv.setVisibility(View.GONE);
                setBtBackground(true);
                loginUsername.setBackgroundResource(R.drawable.line_common_shape);
            }
        } else {

            usernameFlag = false;
            errorTv.setVisibility(View.GONE);
            setBtBackground(false);
            loginUsername.setBackgroundResource(R.drawable.line_common_shape);

        }

    }

    private boolean passwordFlag;

    public void checkPassword() {
        String password = loginPassword.getText().toString().trim();
        if (!TextUtils.isEmpty(password)) {
            if (!FormatUtil.isPassWord(password)) {
                errorTv.setVisibility(View.VISIBLE);
                errorTv.setText("密码格式不对,请使用英文字母数字和特殊符号");
                passwordFlag = false;
                setBtBackground(false);
                loginPassword.setBackgroundResource(R.drawable.line_error_shape);


                return;
            } else {
                passwordFlag = true;
                setBtBackground(true);
                errorTv.setVisibility(View.GONE);
                loginPassword.setBackgroundResource(R.drawable.line_common_shape);

            }
        } else {
            //输入框是空的
            passwordFlag = false;
            setBtBackground(false);
            errorTv.setVisibility(View.GONE);
            loginPassword.setBackgroundResource(R.drawable.line_common_shape);

        }
    }

    @OnClick({R.id.login_button, R.id.weibo_im, R.id.weixin_im, R.id.qq_im})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button:
                //改变按钮背景
                if (usernameFlag && passwordFlag) {
                    Intent intent = new Intent(this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
                break;

            case R.id.weibo_im:
                BaseApplication.showToast("使用微博登录", Toast.LENGTH_LONG);
                break;
            case R.id.weixin_im:
                BaseApplication.showToast("使用微信登录", Toast.LENGTH_LONG);
                break;
            case R.id.qq_im:
                BaseApplication.showToast("使用qq登录", Toast.LENGTH_LONG);
                break;

        }
    }

}