package com.cqgk.clerk.utils;


import com.cqgk.clerk.base.Basic;

/**
 * Created by duke on 15/12/15.
 */
public class DisplayUtil extends Basic {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(double dpValue) {
        try {
            final float scale = getAppContext().getResources().getDisplayMetrics().density;
            return (int) (dpValue * scale + 0.5f);
        } catch (NullPointerException e) {
            return 1;
        }
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(double pxValue) {
        try {
            final float scale = getAppContext().getResources().getDisplayMetrics().density;
            return (int) (pxValue / scale + 0.5f);
        } catch (NullPointerException e) {
            return 1;
        }
    }
}
