package com.cqgk.clerk.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;


import com.cqgk.clerk.activity.LoginActivity;
import com.cqgk.clerk.base.BaseApp;
import com.cqgk.clerk.base.Basic;

import java.io.Serializable;
import java.util.List;


/**
 * 功能界面跳转
 * 全部界面之间跳转通过这里定义
 *
 * @author duke
 */
public class NavigationHelper extends Basic {

    private static NavigationHelper instance = null;


    public NavigationHelper() {
    }

    public static NavigationHelper getInstance() {
        if (instance == null) {
            instance = new NavigationHelper();
        }
        return instance;
    }

    private void startActivity(Context context, Intent intent) {
        context.startActivity(intent);
    }

    private void startActivityForResult(Activity context, Intent intent, int RequestCode) {
        context.startActivityForResult(intent, RequestCode);
    }


    /**
     * 登录界面
     */
    public void startLoginActivity() {
        Intent i = new Intent(getActivity(), LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        if (getAppContext() instanceof BaseApp) {
            this.startActivityForResult(getActivity(), i, 0);
        } else {
            this.startActivityForResult(getActivity(), i, 0);
        }

    }


}
