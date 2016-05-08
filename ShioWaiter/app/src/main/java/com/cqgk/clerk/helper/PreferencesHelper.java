package com.cqgk.clerk.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.cqgk.clerk.base.Basic;


/**
 * 程序全局配置(单例，保持全应用一致处理)
 * @author duke
 */
public class PreferencesHelper extends Basic {

    private static SharedPreferences sharedPreferences;

    public static void init(final Context context) {

        //sharedPreferences = context.getSharedPreferences("morenumber", Context.MODE_PRIVATE);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * 保存key-value到配置文件中
     * @param key
     * @param value
     */
    public static void save(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    /**
     * 保存key-value到配置文件中
     * @param key
     * @param value
     */
    public static void save(String key, int value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }

    /**
     *
     * @param key
     * @param value
     */
    public static void save(String key, long value) {
        sharedPreferences.edit().putLong(key, value).apply();
    }

    /**
     * 保存key-value到配置文件中
     * @param key
     * @param value
     */
    public static void save(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    /**
     * 从配置文件中获取key的value
     * @param key
     */
    public static String find(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    /**
     * 从配置文件中获取key的value
     * @param key
     */
    public static int find(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    /**
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public static long find(String key,long defaultValue){
        return sharedPreferences.getLong(key, defaultValue);
    }

    /**
     * 从配置文件中获取key的value
     * @param key
     */
    public static boolean find(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    public static void clear() {
        sharedPreferences.edit().clear().apply();
    }

    public static void clear(Context context, Class aClass) {
        context.getSharedPreferences(aClass.getName(), Context.MODE_PRIVATE).edit().clear().apply();
    }

}
