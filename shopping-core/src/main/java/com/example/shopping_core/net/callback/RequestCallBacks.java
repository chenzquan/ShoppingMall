package com.example.shopping_core.net.callback;

import android.os.Handler;

import com.example.shopping_core.app.Latte;
import com.example.shopping_core.ui.LatteLoader;
import com.example.shopping_core.ui.LoaderStyle;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 权 on 2018/8/10.
 */

public class RequestCallBacks implements Callback<String>{

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;

    private final LoaderStyle LOADER_SYTLE;

    private static final Handler HANDLER = new Handler();

    public RequestCallBacks(IRequest request,ISuccess success,IFailure failure,IError error,LoaderStyle style){
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.LOADER_SYTLE = style;
    }


    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if(response.isSuccessful()){
            if(call.isExecuted()){
                if(SUCCESS != null){
                    SUCCESS.onSuccess(response.body());
                }
            }
        }else{
            if(ERROR != null){
                ERROR.onError(response.code(),response.message());
            }
        }


        stopLoading();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if(FAILURE != null){
            FAILURE.onFailure();
        }

        if(REQUEST != null){
            REQUEST.onRequestEnd();
        }

        stopLoading();
    }

    private void stopLoading(){
        if(LOADER_SYTLE != null){
            HANDLER.postDelayed(new Runnable() {
                @Override
                public void run() {
                    LatteLoader.stopLoading();
                }
            },1000);
        }
    }

}