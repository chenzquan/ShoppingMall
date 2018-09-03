package com.example.shoppingmall.generators;

import com.example.annotations.EntryGenerator;
import com.example.shopping_core.wechat.templates.WXEntryTemplate;

/**
 * 作者：Created by 权.
 * 时间：2018/9/3.
 */


@EntryGenerator(
        packageName = "com.example.shoppingmall",
        entryTemplate = WXEntryTemplate.class
)
public interface WeChatEntry {
}
