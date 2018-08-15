package com.example.shopping_core.util.storage;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.shopping_core.app.Latte;

/**
 * 作者：Created by 权.
 * 时间：2018/8/15.
 */

public class LattePreference {
    /**
     * 提示:
     * Activity.getPreferences(int mode)生成 Activity名.xml 用于Activity内部存储
     * PreferenceManager.getDefaultSharedPreferences(Context)生成 包名_preferences.xml
     * Context.getSharedPreferences(String name,int mode)生成name.xml
     */

    private static final SharedPreferences PREFERENCES =
            PreferenceManager.getDefaultSharedPreferences(Latte.getApplicationContext());

    private static final String APP_PREFERENCES_KEY = "profile";

    private static SharedPreferences getAppPreferences(){
        return PREFERENCES;
    }

    public static void setAppProfile(String value){
        getAppPreferences().edit()
                .putString(APP_PREFERENCES_KEY,value)
                .apply();
    }


    public static String getAppProfile(){
        return getAppPreferences().getString(APP_PREFERENCES_KEY,null);
    }

    public static void removeAppProfile(){
        getAppPreferences().edit()
                .remove(APP_PREFERENCES_KEY)
                .apply();
    }

    public static void setAppFlag(String key,boolean flag){
        getAppPreferences().edit()
                .putBoolean(key,flag)
                .apply();
    }

    public static boolean getAppFlag(String key){
        return getAppPreferences().getBoolean(key,false);
    }

    public static void addCustomAppProfile(String key,String value){
        getAppPreferences().edit()
                .putString(key,value)
                .apply();
    }

    public static String getCustomAppProfile(String key){
        return getAppPreferences().getString(key,"");
    }


















}
