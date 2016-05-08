package com.cqgk.clerk.base;

import android.os.Message;

/**
 * 线程接口
 * @author duke
 */
public interface IHandler {

    public void onUIHandleMessage(Message msg);

    public void onBackProcessHandleMessage(Message msg);

    public void onCustomUIHandleMessage(Message msg);

    public void onCustomUIHandleMessage(Message msg, int handlerType);

    public void onCustomBackgroundHandleMessage(Message msg);

    public void onCustomBackgroundHandleMessage(Message msg, int handlerType);

}
