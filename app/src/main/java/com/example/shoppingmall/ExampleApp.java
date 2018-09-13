package com.example.shoppingmall;

import android.app.Application;

import com.example.shopping_core.app.Latte;
import com.example.shopping_core.net.interceptors.DebugInterceptor;
import com.example.shopping_ec.database.DatabaseManager;
import com.example.shopping_ec.icon.FontECMedule;
import com.facebook.stetho.Stetho;
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
                .withApiHost("http://127.0.0.1/")
           //     .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .withWeChatAppId("")
                .withWeChatAppSecret("")
                .configue();
        initStetho();
        DatabaseManager.getInstance().init(this);

    }

    public void initStetho(){
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
        .build());
    }


}