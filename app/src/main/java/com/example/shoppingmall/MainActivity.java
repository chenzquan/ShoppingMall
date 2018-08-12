package com.example.shoppingmall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.shopping_core.activities.ProxyActivity;
import com.example.shopping_core.app.Latte;
import com.example.shopping_core.delegates.ShoppingDelegate;

public class MainActivity extends ProxyActivity {

    @Override
    public ShoppingDelegate setRootDelegate() {
        return new ExampleDelegate();  //创一个Fragment
    }

}
