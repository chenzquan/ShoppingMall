package com.example.shopping_core.wechat;

import android.app.Activity;

import com.example.shopping_core.app.ConfigType;
import com.example.shopping_core.app.Latte;
import com.example.shopping_core.wechat.callbacks.IWeChatSignInCallback;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * 作者：Created by 权.
 * 时间：2018/9/5.
 */

public class LatteWeChat {
    public static final String APP_ID = Latte.getConfiguration(ConfigType.WE_CHAT_APP_ID);
    public static final String APP_SECRET = Latte.getConfiguration(ConfigType.WE_CHAT_APP_SECRET);
    private final IWXAPI WXAPI; //微信的登录、支付都要 一个微信接口

    private IWeChatSignInCallback mSignInCallback = null;



    private static final class Holder{
        private static final LatteWeChat INSTANCE = new LatteWeChat();
    }

    public static LatteWeChat getInstance(){
        return Holder.INSTANCE;
    }


    private LatteWeChat(){
        final Activity activity = Latte.getConfiguration(ConfigType.ACTIVITY);
        //通过WXAPIFactory工厂，获取IWXAPI的实例
        WXAPI = WXAPIFactory.createWXAPI(activity,APP_ID,true);

        //将应用的appId注册到微信
        WXAPI.registerApp(APP_ID);

    }

    public final IWXAPI getWXAPI(){
        return WXAPI;
    }

    public final void signIn(){
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req);
    }

    public IWeChatSignInCallback getSignInCallback(){
        return mSignInCallback;
    }

    public LatteWeChat onSignSuccess(IWeChatSignInCallback callback){
        mSignInCallback = callback;
        return this;
    }








}
