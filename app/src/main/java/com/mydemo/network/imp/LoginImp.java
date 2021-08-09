package com.mydemo.network.imp;


import com.mydemo.network.config.ApiManage;
import com.mydemo.network.interfaces.ILogin;


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


   /* public Observable<LoginTestRspBean> login(LoginTestReqBean bean) {
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(bean));
        return (mApi.login(body));
    }*/


    }


