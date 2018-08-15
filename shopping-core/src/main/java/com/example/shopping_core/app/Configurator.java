package com.example.shopping_core.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * Created by 权 on 2018/8/7.
 */
public class Configurator {

    private static final HashMap<Object,Object> LATTE_CONFIGS = new HashMap<>(); //映射
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    //还没有配置好
    private Configurator(){  //构建方法
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),false);

    }

    //配置好了
    public final void configue(){
        initIcons();
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),true);
    }


    private static class Holder{  //静态内部类 实现 单例模式
        private static final Configurator INSTANCE = new Configurator();
    }

    public static Configurator getInstance(){  //返回创建的实例
        return Holder.INSTANCE;
    }

    //返回HashMap
    public final HashMap<Object,Object> getLatteConfigs(){
        return Configurator.LATTE_CONFIGS;
    }


    public final Configurator withApiHost(String host){
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(),host);
        return this;
    }

    private void checkConfiguration(){
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());

        if(!isReady){
            throw new RuntimeException("Configuration is not ready, call configure");

        }

    }


    @SuppressWarnings("unchecked")
    public final <T> T getConfiguration(Enum<ConfigType> key){
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key.name());
    }


    //初始化图标字体
    private void initIcons(){
        if(ICONS.size()>0){
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for(int i=1; i<ICONS.size(); i++){
                initializer.with(ICONS.get(i));
            }
        }
    }


    //添加图标字体的module
    public final Configurator withIcon(IconFontDescriptor descriptor){
        ICONS.add(descriptor);
        return this;
    }


    public final Configurator withInterceptor(Interceptor interceptor){
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigType.INTERCEPTOR.name(),INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptor(ArrayList<Interceptor> interceptors){
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigType.INTERCEPTOR.name(),INTERCEPTORS);
        return this;
    }



}