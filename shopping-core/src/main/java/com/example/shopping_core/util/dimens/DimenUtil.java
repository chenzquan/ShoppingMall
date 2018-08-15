package com.example.shopping_core.util.dimens;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.example.shopping_core.app.Latte;

/**
 * Created by 权 on 2018/8/10.
 */

public class DimenUtil {

    public static int getScreenWidth(){
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();//返回对此资源对象有效的当前显示指标
        return dm.widthPixels; //获取屏幕的宽
    }

    public static int getScreenHeight(){
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();//返回对此资源对象有效的当前显示指标
        return dm.heightPixels; //获取屏幕的高
    }

}
