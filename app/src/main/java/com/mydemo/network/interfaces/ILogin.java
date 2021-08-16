package com.mydemo.network.interfaces;


import com.mydemo.bean.LoginBean;
import com.mydemo.bean.LoginReqBean;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface ILogin {

	/**
	 * 登录
	 * @param
	 */
/*	@POST("login/login")
	public Observable<LoginBean> login(@Body LoginReqBean boay);*/

	@FormUrlEncoded
	@POST("login/login")
	public Observable<LoginBean> login(@Field("name") String name, @Field("password") String password);
}
