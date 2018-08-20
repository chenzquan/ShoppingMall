package com.example.shoppingmall;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.example.shopping_core.activities.ProxyActivity;
import com.example.shopping_core.delegates.ShoppingDelegate;
import com.example.shopping_ec.launcher.LauncherDelegate;
import com.example.shopping_ec.launcher.LauncherScrollDelegate;
import com.example.shopping_ec.sign.ISignListener;
import com.example.shopping_ec.sign.SignInDelegate;
import com.example.shopping_ec.sign.SignUpDelegate;

public class MainActivity extends ProxyActivity implements ISignListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
    }

    @Override
    public ShoppingDelegate setRootDelegate() {
       // return new ExampleDelegate();  //创一个Fragment
      //  return new LauncherDelegate();
//        return new SignUpDelegate();
        return new SignInDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
    }
}
