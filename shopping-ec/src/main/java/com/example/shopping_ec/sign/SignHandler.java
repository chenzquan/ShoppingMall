package com.example.shopping_ec.sign;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.shopping_core.app.AccountManager;
import com.example.shopping_ec.database.DatabaseManager;
import com.example.shopping_ec.database.UserProfile;

/**
 * 作者：Created by 权.
 * 时间：2018/8/19.
 */

public class SignHandler {

    public static void onSignUp(String response,ISignListener signListener){
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId,name,avatar,gender,address);
        DatabaseManager.getInstance().getmDao().insert(profile);

        //已经注册并登录成功了
        AccountManager.setSignState(true);
        signListener.onSignUpSuccess();

    }

    public static void onSignIn(String response, ISignListener mISignListener) {

        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId,name,avatar,gender,address);
        DatabaseManager.getInstance().getmDao().insert(profile);

        //已经注册并登录成功了
        AccountManager.setSignState(true);
        mISignListener.onSignInSuccess();
    }
}
