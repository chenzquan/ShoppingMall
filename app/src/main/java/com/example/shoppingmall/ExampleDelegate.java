package com.example.shoppingmall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.example.shopping_core.delegates.ShoppingDelegate;
import com.example.shopping_core.net.RestClient;
import com.example.shopping_core.net.callback.IError;
import com.example.shopping_core.net.callback.IFailure;
import com.example.shopping_core.net.callback.IRequest;
import com.example.shopping_core.net.callback.ISuccess;

/**
 * Created by 权 on 2018/8/8.
 */

public class ExampleDelegate extends ShoppingDelegate{
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();
    }


    private void testRestClient(){
        RestClient.builder()
                .url("users/Guolei1130") //这个url是设置RestService那注释那里的 部分url！！！
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
//                        Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .request(new IRequest() {
                    @Override
                    public void onRequestStart() {

                    }

                    @Override
                    public void onRequestEnd() {

                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .build()
                .get();

    }

}
