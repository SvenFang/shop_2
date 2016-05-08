package com.cqgk.clerk.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.ClipboardManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;


import com.cqgk.clerk.base.Basic;
import com.cqgk.clerk.view.toast.ToastView;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 应用公共工具类
 *
 * @author duke
 */
public class AppUtil extends Basic {

    public static String getUrlEncoder(String content){
        try {
            return java.net.URLEncoder.encode(content, "utf-8");
        }catch (UnsupportedEncodingException e){

        }

        return content;
    }


    public static Bitmap drawableToBitamp(Drawable drawable) {
        BitmapDrawable bd = (BitmapDrawable) drawable;
        return bd.getBitmap();
    }

    public static String getFotmattoString(String time,String format) {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        return sf.format(new Date(time));
    }


    public static String getTimeStamptoString(int time) {
        return getTimeStamptoString(time, "yyyy-MM-dd HH:mm:ss");
    }

    public static String getTimeStamptoString(int time,String format) {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        return sf.format(new Date(Long.valueOf(time + "000")));
    }

    /**
     * 当前时间戳
     *
     * @return
     */
    public static long getCurrentTimeStamp() {
        Date date = new Date();
        long curTime = date.getTime() / 1000;
        return curTime;
    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public static int getDisplayWidth() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        return width;
    }

    /**
     * @return
     */
    public static String getCurDateString() {
        Date d = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        //LogUtil.d("sign____",sf.format(d));
        return sf.format(d);
    }


    /**
     * 把dip单位转换成像素
     *
     * @param dip
     * @return
     * @deprecated
     */
    public static int dipToPixels(double dip) {
        return (int) (getActivity().getResources().getDisplayMetrics().density * dip);
    }

    public static void logContactsDetails(String title, String[] projection,
                                          ArrayList<String[]> data) {
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.get(i).length; j++) {
                Log.e("wu0wu", projection[j] + "=" + data.get(i)[j]);
            }
        }
    }

    // 剪贴板
    @SuppressWarnings("deprecation")
    public static void copyToClipboard(String contents) {
        ClipboardManager c = (ClipboardManager) getActivity().getSystemService(
                Context.CLIPBOARD_SERVICE);
        c.setText(contents);// 设置Clipboard 的内容
        System.out.println("copyToClipboard..." + c.getText());
    }

    @SuppressWarnings("deprecation")
    public static String getClipboardMessage() {
        ClipboardManager c = (ClipboardManager) getActivity().getSystemService(
                Context.CLIPBOARD_SERVICE);
        System.out.println("getClipboardMessage..." + c.getText());
        String temp = "";
        if (c.getText() != null) {
            temp = c.getText().toString();
        }
        return temp;// 提取clipboard的内容
    }

    private static Toast toast;


    public static void printScreen(Activity activity) {
        Window win = activity.getWindow();
        win.addFlags(WindowManager.LayoutParams.FLAG_SECURE);
    }

    public static void showToast(String text) {
        showToast(getActivity(), text);
    }

    public static void showLongToast(String text) {
        showLongToast(getActivity(), text);
    }

    public static void showToast(Context context, String msg) {
        ToastView toastView = new ToastView(context,msg);
        Toast toast =new Toast(context);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(toastView);
        toast.show();
    }

    public static void showLongToast(Context context, String text) {
        ToastView toastView = new ToastView(context,text);
        Toast toast =new Toast(context);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(toastView);
        toast.show();
    }


    /**
     * 用来判断服务是否运行.
     *
     * @param className 判断的服务名字
     * @return true 在运行 false 不在运行
     */
    public static boolean isServiceRunning(String className) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) getActivity()
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList = activityManager
                .getRunningServices(100);
        if (!(serviceList.size() > 0)) {
            return false;
        }
        for (int i = 0; i < serviceList.size(); i++) {
            System.out.println("start===="
                    + serviceList.get(i).service.getClassName());
            if (serviceList.get(i).service.getClassName().equals(className)) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }

    /**
     * 描述：判断网络是否有效.
     *
     * @return true, if is network available
     */
    public static boolean isNetworkAvailable() {
        try {
            ConnectivityManager connectivity = (ConnectivityManager) getActivity()
                    .getSystemService(
                            Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info != null && info.isConnected()) {
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }


    /**
     * 获取当前版本信息
     *
     * @return
     */
    public static String getVersion() {
        try {
            PackageManager manager = getAppContext().getPackageManager();
            PackageInfo info = manager.getPackageInfo(getAppContext().getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


}
