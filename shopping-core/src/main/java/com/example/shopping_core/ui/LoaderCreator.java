package com.example.shopping_core.ui;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * Created by 权 on 2018/8/10.
 */

/*
用来创建loader
 */

public class LoaderCreator{
    private static final WeakHashMap<String,Indicator> LOADING_MAP = new WeakHashMap<>();

    /*
    创建Loading图
     */
    public static AVLoadingIndicatorView create(String type, Context context){
        final AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);

        if(LOADING_MAP.get(type) == null){
            final Indicator indicator = getIndicator(type);
            LOADING_MAP.put(type,indicator);
        }
        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type)); //已缓存的形式 就不用每次用的时候都反射
        return avLoadingIndicatorView;
    }




    private static Indicator getIndicator(String name){
        if(name == null || name.isEmpty()){
            return null;
        }

        /*
        com.wang.avi.indicators.**  创建这样的一个字符串
         */
        final StringBuilder drawableClassName = new StringBuilder();
        if(!name.contains(".")){
            final String defaultPackName = AVLoadingIndicatorView.class.getPackage().getName();
            drawableClassName.append(defaultPackName)
                    .append(".indicators")
                    .append(".");
        }

        drawableClassName.append(name);
        try {
            final Class<?> drawableClass = Class.forName(drawableClassName.toString());  //获取字符串对应的类
            return (Indicator) drawableClass.newInstance();  //返回这个类的对象
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


}
