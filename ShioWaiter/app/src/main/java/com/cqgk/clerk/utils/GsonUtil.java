/**
 * 文件名:GsonUtil.java
 * 创建人:Sven Fang
 * 创建时间:2015-3-10
 */
package com.cqgk.clerk.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.xutils.common.util.LogUtil;

import java.lang.reflect.Type;

/**
 * @author sven
 *
 */
public class GsonUtil {
    public static <T> T parseGson(String jsonStr, Type clas) {
        try {
            Gson gson = new GsonBuilder().
                    create();
            return gson.fromJson(jsonStr, clas);
        } catch (com.google.gson.JsonSyntaxException e) {
            LogUtil.e(e.toString());
            return null;
        }
    }

}


