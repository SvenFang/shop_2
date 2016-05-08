package com.cqgk.clerk.http;

/**抽象回调接口
 * @param <T>
 * @author duke
 */
public abstract class MethodCallBack<T> {


    public abstract void success(T result);


    /**
     * 返回正确则处理，否则不出
     *
     * @param state
     * @param msg
     * @return
     */
    public boolean failure(int state, String msg) {
        return false;
    }



}
