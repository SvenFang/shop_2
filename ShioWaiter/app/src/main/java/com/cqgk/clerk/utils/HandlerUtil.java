package com.cqgk.clerk.utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;


import com.cqgk.clerk.base.IHandler;
import com.cqgk.clerk.base.SimpleHandler;

import java.util.ArrayList;

/**
 * 
 * @author duke
 *
 */
public class HandlerUtil {

    private static HandlerThread mWorker;

    /**
     * 获得后台线程的looper
     *
     * @return 后台线程的looper
     */
    public static <T> Looper getBackGroundLooper(T t) {
        if (mWorker == null) {
            mWorker = new HandlerThread(t.getClass().getSimpleName(), getWorkLooperThreadPriority());
            mWorker.start();
        }
        return mWorker.getLooper();
    }

    /**
     * 获得后台线程的优先级 默认是Thread.NORM_PRIORITY
     *
     * @return 优先级@see #Thread.NORM_PRIORITY
     */
    public static int getWorkLooperThreadPriority() {
        return android.os.Process.THREAD_PRIORITY_BACKGROUND;
    }

    /**
     * UI线程Handler
     */
    public static <T extends IHandler> SimpleHandler<T> newUIHandlerInstance(T t) {
        return new SimpleHandler<>(t, Looper.getMainLooper(), SimpleHandler.HandlerType.UI);
    }

    /**
     * 后台线程Handler
     */
    public static <T extends IHandler> SimpleHandler<T> newBackProcessHandlerInstance(T t) {
        return new SimpleHandler<>(t, getBackGroundLooper(t), SimpleHandler.HandlerType.BackProcess);
    }

    /**
     * UI线程自定义Handler:默认处理
     */
    public static <T extends IHandler> SimpleHandler<T> newCustomUIHandlerInstance(T t) {
        return new SimpleHandler<>(t, Looper.getMainLooper(), SimpleHandler.HandlerType.CustomUI);
    }

    /**
     * UI线程自定义Handler:根据customHandlerType处理
     */
    public static <T extends IHandler> SimpleHandler<T> newCustomUIHandlerInstance(T t, int customHandlerType) {
        return new SimpleHandler<>(t, Looper.getMainLooper(), SimpleHandler.HandlerType.CustomUI, customHandlerType);
    }

    /**
     * 后台线程自定义Handler:默认处理
     */
    public static <T extends IHandler> SimpleHandler<T> newCustomBackgroundHandlerInstance(T t) {
        return new SimpleHandler<>(t, getBackGroundLooper(t), SimpleHandler.HandlerType.CustomBackGround);
    }

    /**
     * 后台线程自定义Handler:根据customHandlerType处理
     */
    public static <T extends IHandler> SimpleHandler<T> newCustomBackgroundHandlerInstance(T t, int customHandlerType) {
        return new SimpleHandler<>(t, getBackGroundLooper(t), SimpleHandler.HandlerType.CustomBackGround, customHandlerType);
    }

    public static void sendHandler(Handler handler, Message msg) {
        if (handler != null) {
            handler.removeMessages(msg.what);
            handler.sendMessage(msg);
        }
    }

    public static void sendHandler(Handler handler, int what) {
        if (handler != null) {
            handler.removeMessages(what);
            handler.obtainMessage(what).sendToTarget();
        }
    }

    public static void sendHandler(Handler handler, int what, Object obj) {
        if (handler != null) {
            handler.removeMessages(what);
            handler.obtainMessage(what, obj).sendToTarget();
        }
    }

    public static void sendHandler(Handler handler, int what, Bundle data) {
        if (handler != null) {
            handler.removeMessages(what);
            Message msg = handler.obtainMessage(what);
            msg.setData(data);
            handler.sendMessage(msg);
        }
    }

    public static void sendHandler(Handler handler, int what, int arg1, int arg2) {
        if (handler != null) {
            handler.removeMessages(what);
            handler.obtainMessage(what, arg1, arg2).sendToTarget();
        }
    }

    public static void sendHandler(Handler handler, int what, int arg1, int arg2, Bundle data) {
        if (handler != null) {
            handler.removeMessages(what);
            Message msg = handler.obtainMessage(what, arg1, arg2);
            msg.setData(data);
            handler.sendMessage(msg);
        }
    }

    public static void sendHandler(Handler handler, int what, int arg1, int arg2, Object obj) {
        if (handler != null) {
            handler.removeMessages(what);
            handler.obtainMessage(what, arg1, arg2, obj).sendToTarget();
        }
    }

    public static void sendHandler(Handler handler, int what, long delay) {
        if (handler != null) {
            handler.removeMessages(what);
            handler.sendMessageDelayed(handler.obtainMessage(what), delay);
        }
    }

    public static void sendHandler(Handler handler, int what, Object obj, long delay) {
        if (handler != null) {
            handler.removeMessages(what);
            handler.sendMessageDelayed(handler.obtainMessage(what, obj), delay);
        }
    }

    public static void sendHandler(Handler handler, int what, int arg1, Object obj, long delay) {
        if (handler != null) {
            handler.removeMessages(what);
            handler.sendMessageDelayed(handler.obtainMessage(what, arg1, -1, obj), delay);
        }
    }

    public static void postDelayed(Handler handler, Runnable r, long delay) {
        if (handler != null) {
            handler.removeCallbacks(r);
            handler.postDelayed(r, delay);
        }
    }

    public static void removeHandler(Handler handler, int what) {
        if (handler != null) {
            handler.removeMessages(what);
        }
    }

    public static void removeCallbacks(Handler handler, Runnable r) {
        if (handler != null) {
            handler.removeCallbacks(r);
        }
    }

    public static void removeCallbacksAndMessagesOfHandler(Handler handler) {
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    public static void removeCallbacksAndMessagesOfHandler(Handler... handlers) {
        for (Handler handler : handlers) {
            removeCallbacksAndMessagesOfHandler(handler);
        }
    }

    public static void removeCallbacksAndMessagesOfHandler(ArrayList<? extends Handler> handlers) {
        if (handlers == null) {
            return;
        }

        for (int i = 0; i < handlers.size(); i++) {
            Handler handler = handlers.remove(i);
            removeCallbacksAndMessagesOfHandler(handler);
        }
        handlers.clear();
    }
}
