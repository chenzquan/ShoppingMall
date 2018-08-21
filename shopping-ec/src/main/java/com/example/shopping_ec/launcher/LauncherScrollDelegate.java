package com.example.shopping_ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.shopping_core.app.AccountManager;
import com.example.shopping_core.app.IUserChecker;
import com.example.shopping_core.delegates.ShoppingDelegate;
import com.example.shopping_core.ui.launcher.ILauncherListener;
import com.example.shopping_core.ui.launcher.LauncherHolderCreator;
import com.example.shopping_core.ui.launcher.OnLauncherFinishTag;
import com.example.shopping_core.ui.launcher.ScrollLauncherTag;
import com.example.shopping_core.util.storage.LattePreference;
import com.example.shopping_ec.R;

import java.util.ArrayList;

/**
 * 作者：Created by 权.
 * 时间：2018/8/15.
 */

public class LauncherScrollDelegate extends ShoppingDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();

    private ILauncherListener mILauncherListener = null;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(activity instanceof ILauncherListener){
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {
        //检测是否 是最后一个
        if(position == INTEGERS.size() - 1){
            LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(),true);
            //检测用户是否已经登录

            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if(mILauncherListener!=null){
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNoSignIn() {
                    if(mILauncherListener!=null){
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });

        }
    }


    private void initBanner(){
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05);
        mConvenientBanner.setPages(new LauncherHolderCreator(),INTEGERS)
                .setPageIndicator(new int []{R.drawable.dot_normal,R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(true); //设置是否循环
    }


}
