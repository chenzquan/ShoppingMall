package com.example.shopping_core.app;

import android.content.Context;
import java.util.HashMap;

/**
 * Created by 权 on 2018/8/7.
 */

public final class Latte {

    public static Configurator init(Context context){

        Configurator.getInstance()
                .getLatteConfigs()
                .put(ConfigType.APPLICATION_CONTEXT,context.getApplicationContext());
        return Configurator.getInstance();

    }



    public static Context getApplicationContext(){
        return (Context) getConfigurator().getLatteConfigs().get(ConfigType.APPLICATION_CONTEXT);
    }

    public static Configurator getConfigurator(){
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(Object key){
        return getConfigurator().getConfiguration(key);
    }



}