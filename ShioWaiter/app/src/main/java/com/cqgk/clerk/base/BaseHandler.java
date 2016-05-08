package com.cqgk.clerk.base;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * UI线程基类
 * @author duke
 *
 * @param <T>
 */
public abstract class BaseHandler<T> extends Handler {
    private final WeakReference<T> ref;

    public BaseHandler(T t) {
        super();
        ref = new WeakReference<>(t);
    }

    public BaseHandler(T t, Looper looper) {
        super(looper);
        ref = new WeakReference<>(t);
    }

    @Override
    public void handleMessage(Message msg) {
        final T t = ref.get();
        if (t != null) {
            handleMessage(t, msg);
        }
    }

    public abstract void handleMessage(T t, Message msg);
}
