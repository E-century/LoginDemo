package com.mydemo.network.config;

import java.util.HashMap;

/**
 * 请求参数
 */
public class ApiParameter {
    //id
    public static final String ID = "id";

    private final HashMap<String, Object> mParamMap;

    public ApiParameter(){
        mParamMap = new HashMap<>();
    }

    public HashMap<String, Object> build(){
        return mParamMap;
    }

    public ApiParameter addId(String id){
        mParamMap.put(ID,id);
        return this;
    }
}
