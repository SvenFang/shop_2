package com.cqgk.clerk.http;

import com.cqgk.clerk.utils.AppUtil;
import com.cqgk.clerk.utils.CheckUtils;
import com.cqgk.clerk.utils.GsonUtil;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.util.LogUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @param <T>
 * @author duke
 */
public abstract class HttpCallBack<T> {

    private final Type type;

    public HttpCallBack() {
        Type mySuperClass = getClass().getGenericSuperclass();
        type = ((ParameterizedType) mySuperClass).getActualTypeArguments()[0];
    }

    @SuppressWarnings("unchecked")
    void handle(String resultJsonStr) {

        if (!CheckUtils.isAvailable(resultJsonStr)) {
            AppUtil.showToast("resultJsonStr is empty___");
            return;
        }

        ResponseVo responseVo = null;

        try {
            JSONObject jsonObject = new JSONObject(resultJsonStr);
            responseVo = new ResponseVo();
            responseVo.retCode = jsonObject.optInt("code", Integer.MIN_VALUE);
            responseVo.msg = jsonObject.optString("msg");
            responseVo.data = jsonObject.optString("data");
            responseVo.error = jsonObject.optString("error");
            responseVo.detail = jsonObject.optString("detail");

        } catch (JSONException e) {
            e.printStackTrace();
        }


        //返回格式有问题或没值返回
        if (responseVo == null || Integer.MIN_VALUE == responseVo.retCode) {
            if (!failure(Integer.MIN_VALUE, "json format error or no value response")) {
                AppUtil.showToast("json format error or no value response");
            }
            return;
        }


        if (200 == responseVo.retCode) {
            try {
                if (type.toString().contains("java.lang.String")) {
                    success((T) responseVo.data);
                } else {
                    Object object = GsonUtil.parseGson(responseVo.data, type);
                    success((T) object);
                }
                return;
            } catch (Exception e) {
                e.printStackTrace();
                if (!failure(0, "new gson type fail")) {// 失败
                    //AppUtil.showToast("new gson type fail");
                }
            }

        }

        //返回失败
        if (!failure(responseVo.retCode, responseVo.msg)) {

            //AppUtil.showToast(String.format("code:%s,msg:%s", responseVo.retCode, responseVo.msg));
        }

    }

    public abstract void success(T result);

    /**
     * 返回正确则处理，否则不出
     *
     * @param state
     * @param msg
     * @return
     */
    public boolean failure(int state, String msg) {
        LogUtil.e("hanler result faild___");
        return false;
    }

    static class ResponseVo {
        int retCode = Integer.MIN_VALUE;
        String msg;
        String data;
        String error;
        String detail;
    }


}
