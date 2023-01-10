package com.liudonghan.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ADParamsUtils {

    private HashMap<String, String> params = new HashMap();

    public ADParamsUtils() {
//        params.put("token", AccountHelper.getToken());
    }

    public static ADParamsUtils newParams() {
        return new ADParamsUtils();
    }

    public ADParamsUtils put(String key, String value) {
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
