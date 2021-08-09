package com.mydemo.network.config;


import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mydemo.base.AppConfig;
import com.mydemo.utils.RxUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


/**
 * 说明：retrofit2里面的服务类接口，那些后台不必要的字段，如果我们没实际需要上传该字段，应该传一个null或-1过去表示不进行上传该字段
 */

public class ApiManage {
    public static ApiManage mInstance;
    private Retrofit mRetrofit;
    private final static String HEAD = "Authorization";
    private OkHttpClient mOkHttpClient;

    public ApiManage() {
        createRetrofit();
    }

    private void createRetrofit() {
        //初始化Gson
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .create();

        // mOkHttpClient = createOkHttp().build();
        mOkHttpClient = createOkHttp().connectTimeout(15, TimeUnit.SECONDS).build();
        //初始化retrofit
        mRetrofit = new Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(mOkHttpClient)
                .build();
    }

    private OkHttpClient.Builder createOkHttp() {

        //初始化日志
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .sslSocketFactory(RxUtils.createSSLSocketFactory())
                .hostnameVerifier(new RxUtils.TrustAllHostnameVerifier())
                .retryOnConnectionFailure(true)
                .addNetworkInterceptor(interceptor)
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        //添加token
                        Request request = chain.request().newBuilder()
//                                .removeHeader("Authorization")
//                                .addHeader("Authorization", "Bearer " + UserManage.getInstance().getToken())
                                .build();
                        return chain.proceed(request);
                    }
                });
    }

    public static ApiManage getInstance() {
        if (mInstance == null) {
            mInstance = new ApiManage();
        }
        return mInstance;
    }

    public <T> T cteateServer(Class<T> c) {
        return mRetrofit.create(c);
    }

    public static final ApiException throwApiException(ApiResult result) throws ApiException {
        throw new ApiException(result);
    }

    private <T> Observable<ApiResult<T>> validResult(@NonNull Observable<ApiResult<T>> obs) {
        return obs.doOnNext(new Action1<ApiResult<T>>() {
            @Override
            public void call(ApiResult<T> tApiResult) {
                if (!tApiResult.isSuccess()) {
                    throwApiException(tApiResult);
                }
            }
        });
    }

    public <T> Observable<ApiResult<T>> schedulersToUI(@NonNull Observable<ApiResult<T>> obs) {
        Observable<ApiResult<T>> obser = validResult(obs);
        return obser
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
