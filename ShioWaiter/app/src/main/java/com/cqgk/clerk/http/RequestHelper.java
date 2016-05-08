package com.cqgk.clerk.http;


import org.xutils.common.Callback;
import org.xutils.ex.DbException;
import org.xutils.http.RequestParams;

import java.io.File;

/**
 * @author duke
 */
public class RequestHelper {
    public static String LOCALIMG_PATH = "/sdcard/shopwaiter";

//    private static HttpUtils getHttpUtils() {
//        if (httpUtils == null) {
//            httpUtils = new HttpUtils(HttpHelper.HTTP_TIME_OUT);
//        }
//        return httpUtils;
//    }


    public static <T> void sendCusUrlGet(boolean showAlert, String urlAction, HttpCallBack<T> myCallback) {
        new HttpHelper().sendGet(showAlert, urlAction, myCallback);
    }


    public static <T> void sendGet(String urlAction, HttpCallBack<T> myCallback) {
        new HttpHelper().sendGet(true, urlAction, myCallback);
    }

    public static <T> void sendGet(boolean showAlert, String urlAction, HttpCallBack<T> myCallback) {

        new HttpHelper().sendGet(showAlert, urlAction, myCallback);
    }

    public static <T> void sendPost(CommonParams params, HttpCallBack<T> myCallback) {
        new HttpHelper().sendPost(true, params, myCallback);
    }

    public static <T> void sendPost(boolean showAlert, RequestParams params, HttpCallBack<T> myCallback) {
        new HttpHelper().sendPost(showAlert, params, myCallback);
    }


    /**
     * 图片下载
     *
     * @param url
     * @param
     * @return
     */
    public static void download(String url,
                                final String localname,
                                final boolean showurl) {
        File file = new File(LOCALIMG_PATH + localname);
        if (file.exists()) {
            file.delete();
        }

//        try {
//            DownloadManager.getInstance().startDownload(
//                    UrlApi.getRealUrl(url), "appimg_" + System.nanoTime(),
//                    LOCALIMG_PATH + localname, true, false, null);
//        } catch (DbException e) {
//            e.printStackTrace();
//        }
    }

    public static void download(String url,
                                final String localname) {
        download(url, localname, false);
    }


    public static class DownloadCallback implements Callback.CommonCallback<File>, Callback.ProgressCallback<File>, Callback.Cancelable {

        public DownloadCallback() {

        }

        @Override
        public void onLoading(long l, long l1, boolean b) {

        }

        @Override
        public void onCancelled(CancelledException e) {

        }

        @Override
        public void onFinished() {

        }

        @Override
        public void onError(Throwable throwable, boolean b) {

        }

        @Override
        public void onStarted() {

        }

        @Override
        public void onSuccess(File file) {

        }

        @Override
        public void onWaiting() {

        }

        @Override
        public boolean isCancelled() {
            return false;
        }

        @Override
        public void cancel() {

        }
    }
}