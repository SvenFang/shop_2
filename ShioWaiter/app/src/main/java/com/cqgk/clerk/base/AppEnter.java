package com.cqgk.clerk.base;


import android.support.v4.app.Fragment;
/**
 * @author duke
 */
public class AppEnter extends BaseApp {



    /**
     * 身份证token:登陆后唯一
     */
    public static String TOKEN = "";

    /**
     *
     */
    public static IActivity MainActivity;

    /**
     *
     */
    public static Fragment MainFragment;








    /**
     * 退出当前账户
     */
    public static void exitAccount() {

        TOKEN = "";
        exitAllActivity();
    }


}
