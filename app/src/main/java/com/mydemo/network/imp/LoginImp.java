package com.mydemo.network.imp;


import com.google.gson.Gson;
import com.mydemo.bean.LoginBean;
import com.mydemo.bean.LoginReqBean;
import com.mydemo.network.config.ApiManage;
import com.mydemo.network.interfaces.ILogin;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Completable;
import rx.Observable;


public class LoginImp {

    private static LoginImp mInstance;
    private ILogin mApi;

    private LoginImp() {
        mApi = ApiManage.getInstance().cteateServer(ILogin.class);
    }

    public static LoginImp getInstance() {
        if (mInstance == null) {
            mInstance = new LoginImp();
        }
        return mInstance;
    }


    public Observable<LoginBean> login(String username, String password) {
        return (mApi.login(username,password));
    }



}


