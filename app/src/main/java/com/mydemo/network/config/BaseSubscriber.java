package com.mydemo.network.config;

import android.widget.Toast;

import com.mydemo.R;
import com.mydemo.app.BaseApplication;
import com.mydemo.utils.ResourceUtil;

import org.apache.http.conn.ConnectTimeoutException;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import rx.Subscriber;



public abstract class BaseSubscriber<T> extends Subscriber<T> {
    private Toast toast;

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof ConnectTimeoutException || e instanceof SocketTimeoutException || e instanceof SocketException || e instanceof UnknownHostException) {
            BaseApplication.showToast(ResourceUtil.getString(R.string.connectTimeout),500);
            return;
        }

        if (e instanceof ApiException) {
            ApiException exception = (ApiException) e;
        }

    }

    @Override
    public void onNext(T o) {

    }
}
