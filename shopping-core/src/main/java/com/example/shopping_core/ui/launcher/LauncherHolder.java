package com.example.shopping_core.ui.launcher;


import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;

/**
 * 作者：Created by 权.
 * 时间：2018/8/15.
 */

public class LauncherHolder implements Holder<Integer> {

    private AppCompatImageView mAppCompatImageView = null;

    @Override
    public View createView(Context context) {
        mAppCompatImageView = new AppCompatImageView(context);
        return mAppCompatImageView;
    }

    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        mAppCompatImageView.setBackgroundResource(data);
    }
}
