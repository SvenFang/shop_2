package com.cqgk.clerk.base;

import android.os.Looper;
import android.os.Message;

/**
 * 线程聚合
 * @author duke
 * @param <T>
 */
public class SimpleHandler<T extends IHandler> extends BaseHandler<T> {

    public enum HandlerType {
        UI, BackProcess,
        CustomUI, CustomBackGround
    }

    private final HandlerType handlerType;
    private int customHandlerType = -1;

    public SimpleHandler(T handler, HandlerType handlerType) {
        super(handler);
        this.handlerType = handlerType;
    }

    public SimpleHandler(T handler, Looper looper, HandlerType handlerType) {
        super(handler, looper);
        this.handlerType = handlerType;
    }

    public SimpleHandler(T handler, HandlerType handlerType, int customHandlerType) {
        super(handler);
        this.handlerType = handlerType;
        this.customHandlerType = customHandlerType;
    }

    public SimpleHandler(T handler, Looper looper, HandlerType handlerType, int customHandlerType) {
        super(handler, looper);
        this.handlerType = handlerType;
        this.customHandlerType = customHandlerType;
    }

    @Override
    public void handleMessage(T handler, Message msg) {
        if (handlerType == HandlerType.UI) {
            handler.onUIHandleMessage(msg);
            return;
        }

        if (handlerType == HandlerType.BackProcess) {
            handler.onBackProcessHandleMessage(msg);
        }

        if (handlerType == HandlerType.CustomUI) {
            if (customHandlerType == -1) {
                handler.onCustomUIHandleMessage(msg);
            } else {
                handler.onCustomUIHandleMessage(msg, customHandlerType);
            }
        }

        if (handlerType == HandlerType.CustomBackGround) {
            if (customHandlerType == -1) {
                handler.onCustomBackgroundHandleMessage(msg);
            } else {
                handler.onCustomBackgroundHandleMessage(msg, customHandlerType);
            }
        }
    }
}
