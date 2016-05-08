package com.cqgk.clerk.utils;

import org.json.JSONArray;

import java.util.List;
import java.util.Map;

/**
 * 检查工具
 *
 * @author duke
 */
public class CheckUtils {

    public static boolean isAvailable(List list) {
        return list != null && !list.isEmpty();
    }

    public static <T> boolean isAvailable(T[] menus) {
        return menus != null && menus.length > 0;
    }

    public static boolean isAvailable(JSONArray jsonArray) {
        return jsonArray != null && jsonArray.length() > 0;
    }


    public static boolean isAvailable(Map map) {
        return map != null && !map.isEmpty();
    }


    public static boolean isAvailable(String str) {
        return !AbStrUtil.isEmpty(str);
    }

    public static boolean isAvailable(List list, int index) {
        return isAvailable(list) && list.size() > index;
    }
}
