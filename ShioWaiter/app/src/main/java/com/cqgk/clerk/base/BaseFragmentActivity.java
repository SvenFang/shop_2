/**
 * 文件名:BaseActivity.java
 * 创建人:Sven Fang
 * 创建时间:2015-3-5
 */
package com.cqgk.clerk.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.EventLog.Event;
import android.view.Window;


import com.cqgk.clerk.delegate.EventDelegate;
import com.cqgk.clerk.helper.ProgressDialogHelper;

import org.xutils.x;


/**

 */
public class BaseFragmentActivity extends FragmentActivity implements IActivity {

    private EventDelegate eventDelegate;

    public boolean userEventBus() {
        return false;
    }

    @Override
    public void initView() {

    }

    @Override
    public void requestData() {

    }

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//设置横屏切换
        super.onCreate(savedInstanceState);
        Basic.setActivity(this);
        x.view().inject(this);

        if (userEventBus()) {
            if (eventDelegate == null) {
                eventDelegate = new EventDelegate();
            }
            eventDelegate.registerEventBus(this);
        }

        BaseApp.getActivityBuf().get(BaseApp.getKey(this));

        BaseApp.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Basic.setActivity(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void onEventMainThread(Event event) {
    }

    @Override
    protected void onDestroy() {
        if (userEventBus()) {
            eventDelegate.unregisterEventBus(this);
        }
        ProgressDialogHelper.get().destroy();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void finish() {
        AppEnter.removeActivity(this);
        super.finish();
    }


}

