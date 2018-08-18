package com.example.shopping_ec.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

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

public class SignUpDelegate extends ShoppingDelegate {

    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mName = null;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_up_mobile_number)
    TextInputEditText mPhone = null;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText mPassword = null;
    @BindView(R2.id.edit_sign_up_repeat_password)
    TextInputEditText mRePassword = null;


    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp(){
        if(checkForm()){
            RestClient.builder()
                    .url("")
                    .params("name",mName.getText().toString())
                    .params("email",mEmail.getText().toString())
                    .params("phone",mPhone.getText().toString())
                    .params("password",mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {

                        }
                    })
                    .build()
                    .post();



        }else{

            Toast.makeText(getContext(),"验证通过",Toast.LENGTH_SHORT).show();
        }

    }



    @OnClick(R2.id.tv_link_sign_in)
    void onClickLink(){
        getSupportDelegate().start(new SignInDelegate());
    }


    private boolean checkForm(){
        final String name = mName.getText().toString();
        final String email = mEmail.getText().toString();
        final String phone = mPhone.getText().toString();
        final String password = mPassword.getText().toString();
        final String repassword = mRePassword.getText().toString();

        boolean isPass = true;

        if(name.isEmpty()){
            mName.setError("请输入名字");
            isPass = false;
        }else{
            mName.setError(null);
        }


        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        }else{
            mEmail.setError(null);
        }


        if(phone.isEmpty() || phone.length() != 11){
            mPhone.setError("手机号码错误");
            isPass = false;
        }else{
            mPhone.setError(null);
        }



        if(password.isEmpty() || password.length() < 6){
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        }else{
            mPassword.setError(null);
        }


        if(repassword.isEmpty() || repassword.length() < 6 || !repassword.equals(password)){
            mRePassword.setError("请输入名字");
            isPass = false;
        }else{
            mRePassword.setError(null);
        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
