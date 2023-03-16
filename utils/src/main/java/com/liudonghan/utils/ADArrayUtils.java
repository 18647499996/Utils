package com.liudonghan.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:3/16/23
 */
public class ADArrayUtils {
    public static boolean isEmpty(Collection collection) {

        return (collection == null || collection.size() == 0);
    }

    public static boolean isEmpty(Object[] collection) {

        return (collection == null || collection.length == 0);
    }

    public static String listToString(List<Integer> list) {
        if (list == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (int string : list) {
            if (first) {
                first = false;
            } else {
                result.append(",");
            }
            result.append(string);
        }
        return result.toString();
    }

    public static List<String> stringToList(String strs) {
        String str[] = strs.split(",");
        return Arrays.asList(str);
    }

    public static <T> int indexOfFirst(List<T> data, Function<T, Boolean> predicate) {
        int index = 0;
        for (T item : data) {
            try {
                if (predicate.apply(item))
                    return index;
            } catch (Exception e) {
                e.printStackTrace();
            }
            index++;
        }
        return -1;
    }
}
