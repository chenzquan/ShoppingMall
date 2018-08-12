package com.example.shopping_core.net;

import android.content.Context;
import com.example.shopping_core.net.callback.IError;
import com.example.shopping_core.net.callback.IFailure;
import com.example.shopping_core.net.callback.IRequest;
import com.example.shopping_core.net.callback.ISuccess;
import com.example.shopping_core.ui.LoaderStyle;


import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by 权 on 2018/8/9.
 */

public class RestClientBuilder {

    private String mUrl = null;
    private Map<String,Object> PARAMS = RestCreator.getParams();
    private IRequest mRequest = null;
    private ISuccess mSuccess = null;
    private IFailure mFailure = null;
    private IError mError = null;
    private RequestBody mBoby = null;
    private File mFile = null;
    private Context mContext = null;
    private LoaderStyle mLoaderStyle = null;

    RestClientBuilder() {
    }


    public final RestClientBuilder url(String url){
        this.mUrl = url;
        return this;
    }



    public final RestClientBuilder params(WeakHashMap<String,Object> param){
        PARAMS.putAll(param);
        return this;
    }


    public final RestClientBuilder params(String key,Object value){
        this.PARAMS.put(key,value);
        return this;
    }

    public final RestClientBuilder file(File file){
        this.mFile = file;
        return this;
    }

    public final RestClientBuilder file(String file){
        this.mFile = new File(file);
        return this;
    }


    public final RestClientBuilder raw(String raw){
        this.mBoby = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),raw);
        return this;
    }

    public final RestClientBuilder request(IRequest request){
        this.mRequest = request;
        return this;
    }

    public final RestClientBuilder success(ISuccess success){
        this.mSuccess = success;
        return this;
    }


    public final RestClientBuilder failure(IFailure failure){
        this.mFailure = failure;
        return this;
    }


    public final RestClientBuilder error(IError error){
        this.mError = error;
        return this;
    }

    public final RestClientBuilder loader(Context context,LoaderStyle style){
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    public final RestClientBuilder  loader(Context context){
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallSpinFadeLoaderIndicator;
        return this;
    }



    public final RestClient build(){

        return new RestClient(mUrl,
                PARAMS,
                mRequest,
                mSuccess,
                mFailure,
                mError,
                mBoby,
                mFile,
                mLoaderStyle,
                mContext
                );
    }


}
