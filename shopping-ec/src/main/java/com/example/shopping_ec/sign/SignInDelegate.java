package com.example.shopping_ec.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;

import com.example.shopping_core.delegates.ShoppingDelegate;
import com.example.shopping_core.net.RestClient;
import com.example.shopping_core.net.callback.ISuccess;
import com.example.shopping_ec.R;
import com.example.shopping_ec.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：Created by 权.
 * 时间：2018/8/18.
 */

public class SignInDelegate extends ShoppingDelegate {

    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail = null;

    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null;

    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn(){
        if(checkForm()){
            RestClient.builder()
                    .url("")
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {

                        }
                    })
                    .build()
                    .post();
        }
    }

    @OnClick(R2.id.tv_link_sign_in)
    void onClickLink(){
        getSupportDelegate().start(new SignUpDelegate());
    }

    @OnClick(R2.id.icon_sign_in_wechat)
    void onClickWeChat(){

    }



    private boolean checkForm() {
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();

        boolean isPass = true;

        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        }else{
            mEmail.setError(null);
        }


        if(password.isEmpty() || password.length() < 6){
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        }else{
            mPassword.setError(null);
        }

        return isPass;
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}