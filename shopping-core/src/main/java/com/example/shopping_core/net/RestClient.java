package com.example.shopping_core.net;

import android.content.Context;

import com.example.shopping_core.app.Latte;
import com.example.shopping_core.net.callback.IError;
import com.example.shopping_core.net.callback.IFailure;
import com.example.shopping_core.net.callback.IRequest;
import com.example.shopping_core.net.callback.ISuccess;
import com.example.shopping_core.net.callback.RequestCallBacks;
import com.example.shopping_core.ui.LatteLoader;
import com.example.shopping_core.ui.LoaderStyle;
import com.squareup.okhttp.RequestBody;

import java.util.Map;
import java.util.WeakHashMap;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by 权 on 2018/8/9.
 */

//传入什么用什么  就用建造者模式
public class RestClient {

    private final String URL;
    private static final WeakHashMap<String,Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;

    private final RequestBody BODY;

    private final LoaderStyle LOADER_STYLE;

    private final Context CONTEXT;


    public RestClient(String url,
                      Map<String, Object> params,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body,
                      LoaderStyle loader_style,
                      Context context) {
        this.URL = url;
        this.PARAMS.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.LOADER_STYLE = loader_style;
        this.CONTEXT = context;
    }

    public static RestClientBuilder builder(){
        return new RestClientBuilder();
    }

    /*
    网络请求 根据方法判断
     */
    public void request(HttpMethod method){
        final RestService restService = RestCreator.getRestService();
        Call<String> call = null;

        if(REQUEST != null){
            REQUEST.onRequestStart();
        }

        if(LOADER_STYLE != null){   //设置loading图标的类型
            LatteLoader.showLoading(CONTEXT,LOADER_STYLE);
        }


        switch (method){
            case GET:
                call = restService.get(URL,PARAMS);
                break;
            case POST:
                call = restService.post(URL,PARAMS);
                break;
            case PUT:
                call = restService.put(URL,PARAMS);
                break;
            case DELETE:
                call = restService.delete(URL,PARAMS);
                break;
            default:
                break;
        }

        if(call != null){
            call.enqueue(getRequestCallback());
        }


    }

    /*
    创建一个RequestCallbacks
     */
    private Callback<String> getRequestCallback(){
        return new RequestCallBacks(REQUEST,SUCCESS,FAILURE,ERROR,LOADER_STYLE);
    }


    public final void get(){
        request(HttpMethod.GET);
    }

    public final void post(){
        request(HttpMethod.POST);
    }

    public final void put(){
        request(HttpMethod.PUT);
    }

    public final void delete(){
        request(HttpMethod.DELETE);
    }



}
