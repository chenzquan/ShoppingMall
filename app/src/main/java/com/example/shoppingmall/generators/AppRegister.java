package com.example.shoppingmall.generators;


import com.example.annotations.AppRegisterGenerator;
import com.example.shopping_core.wechat.templates.AppRegisterTemplate;


/**
 * 作者：Created by 权.
 * 时间：2018/9/3.
 */

@AppRegisterGenerator(
        packageName = "com.example.shoppingmall",
        registerTemplate = AppRegisterTemplate.class
)
public interface AppRegister {

}
