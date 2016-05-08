package com.cqgk.clerk.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.cqgk.clerk.utils.HandlerUtil;


/**
 * 线程管理-Activity
 * @author duke
 *
 */
public abstract class HandlerActivity extends BaseFragmentActivity implements IHandler {

    private SimpleHandler mUIHandler;
    private SimpleHandler mBackProcessHandler;

    /**
     * UI线程Handler
     *
     * @see #onUIHandleMessage(Message)
     */
    public SimpleHandler getUIHandler() {
        if (mUIHandler == null) {
            mUIHandler = HandlerUtil.newUIHandlerInstance(this);
        }
        return mUIHandler;
    }

    /**
     * 后台线程Handler
     *
     * @see #onBackProcessHandleMessage(Message)
     */
    public SimpleHandler getBackProcessHandler() {
        if (mBackProcessHandler == null) {
            mBackProcessHandler = HandlerUtil.newBackProcessHandlerInstance(this);
        }
        return mBackProcessHandler;
    }

    public void onUIHandleMessage(Message msg) {
    }

    public void onBackProcessHandleMessage(Message msg) {
    }

    public void onCustomUIHandleMessage(Message msg) {
    }

    public void onCustomUIHandleMessage(Message msg, int handlerType) {
    }

    public void onCustomBackgroundHandleMessage(Message msg) {
    }

    public void onCustomBackgroundHandleMessage(Message msg, int handlerType) {
    }

    public void sendHandler(Handler handler, Message msg) {
        HandlerUtil.sendHandler(handler, msg);
    }

    public void sendHandler(Handler handler, int what) {
        HandlerUtil.sendHandler(handler, what);
    }

    public void sendHandler(Handler handler, int what, Object obj) {
        HandlerUtil.sendHandler(handler, what, obj);
    }

    public void sendHandler(Handler handler, int what, Bundle data) {
        HandlerUtil.sendHandler(handler, what, data);
    }

    public void sendHandler(Handler handler, int what, int arg1, int arg2) {
        HandlerUtil.sendHandler(handler, what, arg1, arg2);
    }

    public void sendHandler(Handler handler, int what, int arg1, int arg2, Bundle data) {
        HandlerUtil.sendHandler(handler, what, arg1, arg2, data);
    }

    public void sendHandler(Handler handler, int what, int arg1, int arg2, Object obj) {
        HandlerUtil.sendHandler(handler, what, arg1, arg2, obj);
    }

    public void sendHandler(Handler handler, int what, long delay) {
        HandlerUtil.sendHandler(handler, what, delay);
    }

    public void sendHandler(Handler handler, int what, Object obj, long delay) {
        HandlerUtil.sendHandler(handler, what, obj, delay);
    }

    public void sendHandler(Handler handler, int what, int arg1, Object obj, long delay) {
        HandlerUtil.sendHandler(handler, what, arg1, obj, delay);
    }

    public void postDelayed(Handler handler, Runnable r, long delay) {
        HandlerUtil.postDelayed(handler, r, delay);
    }

    @Override
    public void onDestroy() {
        HandlerUtil.removeCallbacksAndMessagesOfHandler(mUIHandler, mBackProcessHandler);
        super.onDestroy();
    }
}
