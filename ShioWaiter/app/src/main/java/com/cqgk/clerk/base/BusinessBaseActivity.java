package com.cqgk.clerk.base;


import android.view.View;
import android.view.ViewGroup;

import com.cqgk.clerk.delegate.TitleDelegate;


/**
 * 业务层基类，所有业务层的Activity都继承于此
 * @author duke
 *
 */
public class BusinessBaseActivity extends CommonActivity {

    private TitleDelegate titleDelegate;

    public void enableTitleDelegate() {
        titleDelegate = new TitleDelegate(this);
    }

    public TitleDelegate getTitleDelegate() {
        return titleDelegate;
    }



    protected View getRootView()
    {
        return ((ViewGroup)findViewById(android.R.id.content)).getChildAt(0);
    }





}
