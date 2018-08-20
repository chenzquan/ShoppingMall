package com.example.shopping_core.app;

import com.example.shopping_core.util.storage.LattePreference;

/**
 * 作者：Created by 权.
 * 时间：2018/8/20.
 */

public class AccountManager {


    private enum SignTag{
        SIGN_TAG
    }


    //保存用户的登录状态，登录后调用
    public static void setSignState(boolean state){
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(),state);
    }

    private static boolean isSignIn(){
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker){
        if(isSignIn()){
            checker.onSignIn();
        }else{
            checker.onNoSignIn();
        }
    }





}
