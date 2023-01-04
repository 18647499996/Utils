package com.liudonghan.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ParamsUtils {

    private HashMap<String, String> params = new HashMap();

    public ParamsUtils() {
//        params.put("token", AccountHelper.getToken());
    }

    public static ParamsUtils newParams() {
        return new ParamsUtils();
    }

    public ParamsUtils put(String key, String value) {
        params.put(key, value);
        return this;
    }

    public Map<String, String> params() {
        //  删除value 为null 的元素
        for (Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<String, String> next = iterator.next();
            if (next.getValue() == null) {
                iterator.remove();
            }
        }
        return params;
    }

}
