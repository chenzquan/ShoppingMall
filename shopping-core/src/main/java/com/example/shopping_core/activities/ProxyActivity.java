package com.example.shopping_core.activities;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.example.shopping_core.R;
import com.example.shopping_core.delegates.ShoppingDelegate;

import me.yokeyword.fragmentation.SupportActivity;



public abstract class ProxyActivity extends SupportActivity{

    public abstract ShoppingDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }


    private void initContainer(@Nullable Bundle savedInstanceState){
        final ContentFrameLayout contentFrameLayout = new ContentFrameLayout(this);//创建ContentFrameLayout实例用来放fragment
        contentFrameLayout.setId(R.id.delegate_container); //把这个ContentFrameLayout设置一个Id
        setContentView(contentFrameLayout);
        if(savedInstanceState == null){
            loadRootFragment(R.id.delegate_container,setRootDelegate()); //加载fragment
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
