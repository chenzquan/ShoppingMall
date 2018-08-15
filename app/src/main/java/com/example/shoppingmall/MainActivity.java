package com.example.shoppingmall;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.example.shopping_core.activities.ProxyActivity;
import com.example.shopping_core.delegates.ShoppingDelegate;
import com.example.shopping_ec.launcher.LauncherDelegate;
import com.example.shopping_ec.launcher.LauncherScrollDelegate;

public class MainActivity extends ProxyActivity {

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
        return new LauncherDelegate();
    }

}
