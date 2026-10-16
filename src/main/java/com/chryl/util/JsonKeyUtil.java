package com.chryl.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by Chr.yl on 2026/7/13.
 *
 * @author Chr.yl
 */
public class JsonKeyUtil {

    /**
     * 递归遍历所有层级，判断整个json里是否存在指定key
     *
     * @param obj       待遍历JSON对象/数组
     * @param targetKey 要查找的key
     * @return true=任意层级存在该key
     */
    public static boolean hasAnyKey(Object obj, String targetKey) {
        // 1. 当前是JSONObject
        if (obj instanceof JSONObject) {
            JSONObject jsonObj = (JSONObject) obj;
            // 当前层直接匹配key
            if (jsonObj.containsKey(targetKey)) {
                return true;
            }
            // 遍历所有value，递归深层
            for (Object val : jsonObj.values()) {
                if (hasAnyKey(val, targetKey)) {
                    return true;
                }
            }
        }
        // 2. 当前是JSON数组，遍历数组每一项递归
        else if (obj instanceof JSONArray) {
            JSONArray jsonArr = (JSONArray) obj;
            for (Object item : jsonArr) {
                if (hasAnyKey(item, targetKey)) {
                    return true;
                }
            }
        }
        // 普通字符串/数字/null，无嵌套，直接返回false
        return false;
    }
}