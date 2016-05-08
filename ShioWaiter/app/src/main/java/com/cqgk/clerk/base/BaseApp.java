package com.cqgk.clerk.base;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.graphics.Bitmap;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.cqgk.clerk.BuildConfig;
import com.cqgk.clerk.helper.ImageHelper;
import com.cqgk.clerk.helper.PreferencesHelper;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 */
public class BaseApp extends Application {


    public static BaseApp Context;


    @Override
    public void onCreate() {
        super.onCreate();
        Context = this;
        //PlatformConfig.setWeixin(Constant.payAppId, Constant.appSecret);//友盟微信服务
        Basic.setAppContext(this);
        //PreferencesHelper.init(this);//全局参服务


        //图片服务
        ImageHelper.getInstance().init();
        initImageLoader(getApplicationContext());


        //推送服务
//        mPushAgent = PushAgent.getInstance(this);
//        mPushAgent.setDebugMode(BuildConfig.DEBUG);
//        mPushAgent.setDisplayNotificationNumber(10);
//        setPushService();
        //推送服务_end

        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);

        //美洽
        //MQManageInit();
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
    }


    /**
     * 设置友盟push系统
     */
//    private void setPushService() {
//        if (PreferencesHelper.find(Key.PUSH, true)) {
//            mPushAgent.enable();
//        } else {
//            mPushAgent.disable();
//        }
//    }

    /**
     * 图片组件初始化
     *
     * @param context
     */
    public static void initImageLoader(android.content.Context context) {
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(800 * 1024 * 1024); // 800 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.memoryCache(new WeakMemoryCache());
        config.threadPoolSize(3);
        if (BuildConfig.DEBUG)
            config.writeDebugLogs();
        ImageLoader.getInstance().init(config.build());
    }


    /**
     *
     */
    public static void exitApp() {
        // 清除通知中信息
        NotificationManager nm = (NotificationManager) Context.getSystemService(NOTIFICATION_SERVICE);
        nm.cancelAll();

        // 清空Activity
        exitAllActivity();
    }

    /**
     * 管理Activity
     */
    private static HashMap<String, IActivity> activityBuf = new HashMap<>();

    public static String getKey(IActivity activity) {
        return String.valueOf(activity.hashCode());
    }

    public static void addActivity(IActivity activity) {
        if (activityBuf.get(getKey(activity)) == null) {
            activityBuf.put(getKey(activity), activity);
        }
    }

    public static void removeActivity(IActivity activity) {
        if (activityBuf.get(getKey(activity)) != null) {
            activityBuf.remove(getKey(activity));
        }
    }

    public static void exitAllActivityButOne(IActivity activity) {
        ArrayList<IActivity> removeList = new ArrayList<>(activityBuf.size());
        for (Iterator<Map.Entry<String, IActivity>> iterator = activityBuf.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<String, IActivity> entry = iterator.next();
            if (!entry.getKey().equals(getKey(activity))) {
                removeList.add(entry.getValue());
            }
        }
        exitActivityList(removeList);
    }

    public static void exitAllActivity() {
        ArrayList<IActivity> removeList = new ArrayList<>(activityBuf.size());
        for (Iterator<Map.Entry<String, IActivity>> iterator = activityBuf.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<String, IActivity> entry = iterator.next();
            removeList.add(entry.getValue());
        }
        exitActivityList(removeList);
        activityBuf.clear();
    }

    public static void exitActivityList(ArrayList<IActivity> iActivities) {
        for (IActivity activity : iActivities) {
            if (activity != null) {
                activity.finish();
            }
        }
        iActivities.clear();
    }

    public static BaseApp getInstance() {
        return Context;
    }


    public static HashMap<String, IActivity> getActivityBuf() {
        return activityBuf;
    }

    public static void setActivityBuf(HashMap<String, IActivity> activityBuf) {
        BaseApp.activityBuf = activityBuf;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
