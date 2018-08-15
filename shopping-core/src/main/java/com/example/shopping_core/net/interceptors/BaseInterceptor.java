package com.example.shopping_core.net.interceptors;


import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;


/**
 * 作者：Created by 权.
 * 时间：2018/8/13.
 */

public abstract class BaseInterceptor implements Interceptor {

    protected LinkedHashMap<String,String> getUrlParameters(Chain chain){
        final HttpUrl url = chain.request().url();  //获取请求的Url
        int size = url.querySize();  //请求参数个数
        final LinkedHashMap<String,String> params = new LinkedHashMap<>();
        for(int i=0; i<size; i++){
            params.put(url.queryParameterName(i),url.queryParameterValue(i));
        }
        return params;
    }

    protected String getUrlParameters(Chain chain,String key){
        final HttpUrl url = chain.request().url();
        return url.queryParameter(key);
    }

    protected LinkedHashMap<String,String> getBodyParameters(Chain chain){
        final FormBody formBody = (FormBody) chain.request().body();
        final LinkedHashMap<String,String> params = new LinkedHashMap<>();
        int size = 0;

        if(formBody != null){
            size = formBody.size();
        }
        for(int i=0; i<size; i++){
            params.put(formBody.name(i),formBody.value(i));
        }

        return params;
    }

    protected String getBodyParameters(Chain chain,String key){
        LinkedHashMap<String,String> params = getBodyParameters(chain);
        return params.get(key);
    }





}
