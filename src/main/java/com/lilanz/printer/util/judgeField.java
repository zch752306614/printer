package com.lilanz.printer.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.thymeleaf.util.StringUtils;

import java.lang.reflect.Field;

public class judgeField {
    public static Boolean isExistFieldByObject(String fieldName, Object obj) {
        if (obj == null || StringUtils.isEmpty(fieldName)) {
            return null;
        }
        //获取这个类的所有属性
        Field[] fields = obj.getClass().getDeclaredFields();
        boolean flag = false;
        //循环遍历所有的fields
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getName().equals(fieldName)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public static Boolean isExistFieldByJson(String field, Object obj) {
        if (obj == null || StringUtils.isEmpty(field)) {
            return null;
        }
        Object o = JSON.toJSON(obj);
        JSONObject jsonObj = new JSONObject();
        if (o instanceof JSONObject) {
            jsonObj = (JSONObject) o;
        }
        return jsonObj.containsKey(field);
    }

}
