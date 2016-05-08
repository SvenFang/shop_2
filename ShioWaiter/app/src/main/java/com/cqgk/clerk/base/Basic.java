package com.cqgk.clerk.base;

import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;

/**
 * 全局APP基类
 * @author duke
 *
 */
public class Basic {

    private static BaseApp AppContext;
    private static FragmentActivity Activity;

    public static void setActivity(FragmentActivity mActivity) {
        Basic.Activity = mActivity;
    }

    protected static FragmentActivity getActivity() {
        return Activity;
    }

    protected static BaseApp getAppContext() {
        return AppContext;
    }

    public static void setAppContext(BaseApp appContext) {
        AppContext = appContext;
    }

    public static Resources getResources() {
        return getAppContext().getResources();
    }
}
