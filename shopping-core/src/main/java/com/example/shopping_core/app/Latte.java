package com.example.shopping_core.app;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by 权 on 2018/8/7.
 */

public final class Latte {

    public static Configurator init(Context context){
        getConfigurations().
                put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static HashMap<Object,Object> getConfigurations(){
        return Configurator.getInstance().getLatteConfigs();
    }


    public static Context getApplicationContext(){
        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }


}