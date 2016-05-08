/**
 *  文件名:LogUtil.java
 *  创建人:Sven Fang
 *  创建时间:2015-3-2
 */
package com.cqgk.clerk.utils;

import android.util.Log;


/**
 *
 */
public class LogUtil {

    public static final boolean TEST = true;
    private static final String defTagStr="shopwaiterapp";

    public static void e(String text) {
        e(defTagStr, text, null);
    }

    public static void e(String tag, String text) {
        e(tag, text, null);
    }

    public static void e(String tag, String text, Throwable r) {
        if (TEST) {
            if (r == null) {
                Log.e(tag, text);
            } else {
                Log.e(tag, text, r);
            }
        }
    }

    public static void i(String tag, String text) {
        i(tag, text, null);
    }

    public static void i(String tag, String text, Throwable r) {
        if (TEST) {
            if (r == null) {
                Log.i(tag, text);
            } else {
                Log.i(tag, text, r);
            }
        }
    }

    public static void v(String tag, String text) {
        v(tag, text, null);
    }

    public static void v(String tag, String text, Throwable r) {
        if (TEST) {
            if (r == null) {
                Log.v(tag, text);
            } else {
                Log.v(tag, text, r);
            }
        }
    }

    public static void w(String tag, String text) {
        w(tag, text, null);
    }

    public static void w(String tag, String text, Throwable r) {
        if (TEST) {
            if (r == null) {
                Log.w(tag, text);
            } else {
                Log.w(tag, text, r);
            }
        }
    }

    public static void d(String tag, String text) {
        d(tag, text, null);
    }

    public static void d(String tag, String text, Throwable r) {
        if (TEST) {
            if (r == null) {
                Log.d(tag, text);
            } else {
                Log.d(tag, text, r);
            }
        }
    }
}
