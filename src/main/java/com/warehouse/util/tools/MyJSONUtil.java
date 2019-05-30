package com.warehouse.util.tools;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @Author: komi
 * @Date: 2018/10/31 10:23
 */
public class MyJSONUtil {

    /**
     * 判断字符串是否可以转化为JSON对象
     * @param content
     * @return
     */
    public static boolean isJSONObject(String content){
        try {
            JSONObject jsonStr= JSONObject.parseObject(content);
            return  true;
        } catch (Exception e) {
            System.out.print(e);
            return false;
        }
    }

    /**
     * 判断字符串是否可以转化为JSON数组
     * @param content
     * @return
     */
    public static boolean isJSONArray(String content){
        try {
            JSONArray jsonStr= JSONObject.parseArray(content);
            return  true;
        } catch (Exception e) {
            System.out.print(e);
            return false;
        }
    }
}
