package com.example.shopping_core.delegates.bottom;

import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.example.shopping_core.R;
import com.example.shopping_core.delegates.ShoppingDelegate;

/**
 * 作者：Created by 权.
 * 时间：2018/9/12.
 */

public abstract class BottomItemDelegate extends ShoppingDelegate implements View.OnKeyListener{

    private long mExitTime = 0;
    private static final int EXIT_TIME = 2000;

    @Override
    public void onResume() {
        super.onResume();
        View rootView = getView();
        if(rootView != null){
            rootView.setFocusableInTouchMode(true);
            rootView.requestFocus();
            rootView.setOnKeyListener(this);
        }
    }

    @Override
    public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
        if(keyCode == KeyEvent.KEYCODE_BACK && keyEvent.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis() - mExitTime) > EXIT_TIME){
                Toast.makeText(getContext(),"双击退出" + getString(R.string.app_name),Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            }else{
                _mActivity.finish();
                if(mExitTime != 0){
                    mExitTime = 0;
                }
            }
            return true;
        }
        return false;
    }


}
