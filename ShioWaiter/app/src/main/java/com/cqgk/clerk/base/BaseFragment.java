/**
 * 文件名:BaseFragment.java
 * 创建人:Sven Fang
 * 创建时间:2015-3-5
 */
package com.cqgk.clerk.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cqgk.clerk.delegate.EventDelegate;

import junit.framework.Assert;

import org.xutils.x;

/**
 *
 * @author duke
 *
 */
public class BaseFragment extends Fragment {

    private BaseFragmentActivity activity;
    private boolean isAlive = false;
    private EventDelegate eventDelegate;
    private boolean injected = false;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        Assert.assertTrue(activity.toString() + " must be FragmentBaseActivity!", activity instanceof BaseFragmentActivity);
        this.activity = (BaseFragmentActivity) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private synchronized void setAlive(boolean alive) {
        isAlive = alive;
    }

    /**
     * 当界面生命周期处于onViewCreated之后和onDestroyView之前时为true,否则为false
     *
     * @return
     */
    public synchronized boolean isAlive() {
        return isAlive;
    }

    public boolean userEventBus() {
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        injected = true;
        return x.view().inject(this, inflater, container);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setAlive(true);
        if (eventDelegate == null) {
            eventDelegate = new EventDelegate();
        }
        eventDelegate.registerEventBus(this);
        if (!injected) {
            x.view().inject(this, this.getView());
        }
    }

    @Override
    public void onDestroyView() {
        if (userEventBus()) {
            eventDelegate.unregisterEventBus(this);
        }
        setAlive(false);
        super.onDestroyView();
    }

    /**
     *
     * @param keyCode
     * @param com.lib.event
     * @return true表示拦截当前点击事件
     */
/*    public boolean onKeyDown(int keyCode, KeyEvent com.lib.event) {
        return false;
    }*/

    /**
     *
     * @return true表示拦截当前点击事件
     */
    public boolean onBackPressed() {
        return false;
    }

    public void finish() {

    }
}
