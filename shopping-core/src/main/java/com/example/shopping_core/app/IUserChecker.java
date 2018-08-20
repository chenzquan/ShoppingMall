package com.example.shopping_core.app;

/**
 * 作者：Created by 权.
 * 时间：2018/8/20.
 */

/**
 * 有没有登录
 */
public interface IUserChecker {

    void onSignIn();
    void onNoSignIn();

}
