package com.example.shoppingmall.generators;


import com.example.annotations.PayEntryGenerator;
import com.example.shopping_core.wechat.templates.WPayXEntryTemplate;


/**
 * 作者：Created by 权.
 * 时间：2018/9/3.
 */


@PayEntryGenerator(
        packageName = "com.example.shoppingmall",
        payEntryTemplate = WPayXEntryTemplate.class
)
public interface WeChatPayEntry {
}
