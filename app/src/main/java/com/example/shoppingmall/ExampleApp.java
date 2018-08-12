package com.example.shoppingmall;

import android.app.Application;

import com.example.shopping_core.app.Latte;
import com.example.shopping_ec.icon.FontECMedule;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by 权 on 2018/8/7.
 */

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontECMedule())
                .withIcon(new FontAwesomeModule())
                .withApiHost("https://api.github.com/")
                .configue();


    }
}