package com.cqgk.clerk.http;



import com.cqgk.clerk.bean.dbbean.ResponBean;
import com.cqgk.clerk.helper.NavigationHelper;
import com.cqgk.clerk.helper.ProgressDialogHelper;
import com.cqgk.clerk.logic.db.ResponLogic;
import com.cqgk.clerk.utils.AppUtil;
import com.cqgk.clerk.utils.LogUtil;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;


/**
 * 网络服务请求回调入口
 *
 * @author duke
 */
public class HttpHelper
        implements
        Callback.ProgressCallback<CommonHttpResponse>,
        Callback.CommonCallback<CommonHttpResponse> {

    private static final boolean Print = true;
    public static HttpHelper mHelper;
    private boolean showAlert = false;
    private HttpCallBack callBack;
    private String gloUrl = "";

    /**
     * 除非同步，不然尽量不用单例
     *
     * @return
     */
    public static HttpHelper get() {
        if (mHelper == null) {
            mHelper = new HttpHelper();
        }
        return mHelper;
    }

    public HttpHelper() {
        init();
    }

    private void init() {

    }


    public <T> void sendGet(String url, HttpCallBack<T> myCallback) {
        gloUrl = url;
        sendGet(true, url, myCallback);
    }


    public <T> void sendGet(boolean showAlert, String url, HttpCallBack<T> myCallback) {
        gloUrl = url;

        this.showAlert = showAlert;
        this.callBack = myCallback;


        CommonParams params = new CommonParams(url);
        params.setCharset("utf-8");

        if (Print) {
            printParams(params);
        }
        x.http().get(params, this);


    }


    public <T> void sendPost(RequestParams params, HttpCallBack<T> myCallback) {
        sendPost(true, params, myCallback);
    }

    public <T> void sendPost(boolean showAlert, RequestParams params, HttpCallBack<T> myCallback) {
        if (!AppUtil.isNetworkAvailable()) {
            if (showAlert)
                AppUtil.showToast("网络未连接!");
        } else {
            if (Print) {
                printParams(params);
            }
            this.showAlert = showAlert;
            this.callBack = myCallback;
            x.http().post(params, this);
        }


    }

    private void printParams(RequestParams params) {
        LogUtil.w("HttpRequest", "URL: " + params.getUri());

        if (params.getQueryStringParams() != null) {
            LogUtil.w("HttpRequest", String.format("___params:%s", params.getQueryStringParams().toString()));
        }

        if (params.getHeaders() != null && params.getHeaders().size() > 0) {
            LogUtil.w("HttpRequest", String.format("____headers:Auth-Token:%s,private-key:%s,appid:%s,device_token:%s",

                    params.getHeaders().get(0).getValueStr(),
                    params.getHeaders().get(1).getValueStr(),
                    params.getHeaders().get(2).getValueStr(),
                    params.getHeaders().get(3).getValueStr()));
        }
    }


    @Override
    public void onWaiting() {

    }

    @Override
    public void onStarted() {
        if (showAlert)
            ProgressDialogHelper.get().show();
    }

    private void saveCache(String url, String responsestr) {
        ResponLogic.saveRespon(url, responsestr);
    }

    @Override
    public void onSuccess(CommonHttpResponse response) {

        //记录缓存
        saveCache(response.getRequestUrl(), response.getContent());

        if (Print) {
            LogUtil.e(response.getContent());
        }

        if (callBack != null) {
            callBack.handle(response.getContent());
        }

        if (showAlert) {
            ProgressDialogHelper.get().dismiss();
        }
    }

    @Override
    public void onError(Throwable ex, boolean b) {
        org.xutils.ex.HttpException e;
        if (showAlert) {
            ProgressDialogHelper.get().dismiss();
        }


        if (ex instanceof org.xutils.ex.HttpException) { // 网络错误

            e = (org.xutils.ex.HttpException) ex;
            int responseCode = e.getCode();
            String s = e.getMessage();


            ex.printStackTrace();

            if (s.contains("ConnectTimeoutException")) {
                AppUtil.showLongToast("服务器连接超时，请检查服务器地址是否设置错误，或者联系客服人员！");
            } else if (e.getCode() == 400) {
                AppUtil.showLongToast("服务器地址请求无效，请检查服务器地址是否设置错误！");
            } else if (e.getCode() == 403) {
                AppUtil.showLongToast("服务器地址禁止访问，请联系客服人员！");
            } else if (e.getCode() == 404) {
                AppUtil.showLongToast("服务器无法找到，请检查服务器地址是否设置错误，或者联系客服人员！");
            } else if (e.getCode() == 405) {//TOKEN过期
                LogUtil.w(this.getClass().getName(), "资源被禁止，无法访问，请联系客服人员！TOKEN过期");




                loginEvent();


            } else if (e.getCode() == 410) {
                AppUtil.showLongToast("服务器地址永远不可用，请检查服务器地址是否设置错误！");
            } else if (e.getCode() == 414) {
                AppUtil.showLongToast("服务器地址请求无效，请求URI太长！");
            } else if (e.getCode() == 500) {
                AppUtil.showLongToast("内部服务器错误，请联系客服人员！");
            } else if (e.getCode() == 502) {
                AppUtil.showLongToast("网关错误，请检查网络相关配置！");
            } else if (e.getCode() == 503) {
                AppUtil.showLongToast("因暂时超载或临时维护，您的Web 服务器目前无法处理HTTP 请求!");
            } else if (e.getCode() == 0) {
                AppUtil.showLongToast("服务器地址无法访问，请检查服务器地址是否设置错误！");
            } else if (e.getCode() == 401) {
                LogUtil.w(this.getClass().getName(), "服务器地址无法访问，未授权： (Unauthorized)！");




                loginEvent();
            } else if (e.getCode() == 407) {
                AppUtil.showLongToast("您的购物车没有商品,或是商品已失效!");
            } else if (e.getCode() == 406) {
                AppUtil.showLongToast("服务器地址请求无效,请稍后再试");
            }
        }



        //错误回调
        if (callBack != null) {
            ResponBean bean = ResponLogic.findRespon(UrlApi.getApiUrl(gloUrl));//判断是否有本地数据库缓存
            if (bean != null) {
                callBack.handle(bean.getResponstr());
                return;
            }
            callBack.failure(9, ex.getMessage());
        }

    }

    /**
     * 触发登录事件
     */
    private void loginEvent() {
        NavigationHelper.getInstance().startLoginActivity();
    }

    @Override
    public void onFinished() {

    }

    @Override
    public void onCancelled(CancelledException e) {

    }

    @Override
    public void onLoading(long l, long l1, boolean b) {

    }

}
