package com.example.shopping_core.wechat.templates;

import com.example.shopping_core.activities.ProxyActivity;
import com.example.shopping_core.delegates.ShoppingDelegate;
import com.example.shopping_core.wechat.LatteWeChat;

/**
 * 作者：Created by 权.
 * 时间：2018/9/3.
 */


//微信登录完了 返回这个Activity
public class WXEntryTemplate extends BaseWXEntryActivity{


    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0,0);//不需要动画
    }


    @Override
    protected void onSignInSuccess(String userInfo) {
        LatteWeChat.getInstance().getSignInCallback().onSignInSuccess(userInfo);
    }
}
