package com.example.shopping_ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;


import com.example.shopping_core.delegates.ShoppingDelegate;
import com.example.shopping_core.ui.launcher.ScrollLauncherTag;
import com.example.shopping_core.util.storage.LattePreference;
import com.example.shopping_core.util.timer.BaseTimerTask;
import com.example.shopping_core.util.timer.ITimerListener;
import com.example.shopping_ec.R;
import com.example.shopping_ec.R2;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：Created by 权.
 * 时间：2018/8/15.
 */

public class LauncherDelegate extends ShoppingDelegate implements ITimerListener{

    private Timer mTimer = null;
    private int mCount = 5;

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer = null;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimeView(){
        if(mTimer!=null){
            mTimer.cancel();
            mTimer = null;

            checkIsShowScroll();
        }
    }


    private void initTimer(){
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task,0,1000);

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mTvTimer!=null){
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s",mCount));
                    mCount--;
                }

                if(mCount < 0){
                    if(mTimer != null){
                        mTimer.cancel();
                        mTimer = null;

                        checkIsShowScroll();
                    }
                }

            }
        });
    }


    //判断是否显示滑动页面
    private void checkIsShowScroll(){
        if(!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())){
            start(new LauncherScrollDelegate(),SINGLETASK);
        }else{
            //检测用户是否登录了APP

        }
    }


}
