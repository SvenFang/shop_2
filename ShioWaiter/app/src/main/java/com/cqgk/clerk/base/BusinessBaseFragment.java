package com.cqgk.clerk.base;


import com.cqgk.clerk.delegate.TitleDelegate;

/**
 * 业务层基类，所有业务层的Fragment都继承于此
 * @author duke
 *
 */
public class BusinessBaseFragment extends CommonFragment {

    private TitleDelegate titleDelegate;

    public void enableTitleDelegate() {
        titleDelegate = new TitleDelegate(this);
    }

    public TitleDelegate getTitleDelegate() {
        return titleDelegate;
    }



}
