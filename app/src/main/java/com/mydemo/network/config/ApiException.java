package com.mydemo.network.config;


import com.mydemo.app.BaseApplication;

public class ApiException extends RuntimeException {
    private String mErrorMsg = null;
    private boolean mStatusCode;
    public ApiException() { }
    public ApiException(Object obj) {
        if (obj == null) {
            mErrorMsg = "未知错误";
        } else {
            if (obj instanceof ApiResult) {
                mErrorMsg = ((ApiResult) ((ApiResult) obj)).getMessage();
            } else {
                mErrorMsg = String.valueOf(obj);
            }
        }
        BaseApplication.showToast(mErrorMsg, 500);
    }

    public boolean getStatusCode() {
        return mStatusCode;
    }

    @Override
    public String getMessage() {
        if (mErrorMsg != null) {
            return mErrorMsg;
        } else {
            return super.getMessage();
        }
    }

    @Override
    public String toString() {
        return "api exception :" + getMessage();
    }
}
