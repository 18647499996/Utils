package com.liudonghan.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Description
 *
 * @author Created by: Li_Min
 * Time:2018/8/4
 */
public class ADJsonUtils {

    public static final String TAG = "Mac_Liu";
    public static final String UTF_8 = "utf-8";

    static {
        mGson = new GsonBuilder().disableHtmlEscaping().create();
    }

    private static Gson mGson;

    /**
     * 序列化json字符串
     *
     * @param object 对象
     * @return String
     */
    public static String toJson(Object object) {
        if (object == null) {
            return null;
        }
        return mGson.toJson(object);

    }

    /**
     * 序列化json对象
     *
     * @param json json数据
     * @param t    Type类型
     * @return T
     */
    public static <T> T fromJson(String json, Type t) throws RuntimeException {
        T t1 = null;
        try {
            t1 = mGson.fromJson(json, t);

        } catch (RuntimeException e) {
            Log.e(TAG,"JsonSyntax Error：" + json);
            e.printStackTrace();
            return null;
        }
        return t1;

    }

    /**
     * 序列化json对象
     *
     * @param json json数据
     * @param t    序列化对象T
     * @return T
     */
    public static <T> T fromJson(String json, Class<T> t) throws RuntimeException {
        T t1 = null;
        try {
            t1 = mGson.fromJson(json, t);

        } catch (RuntimeException e) {
            Log.e(TAG, "JsonSyntax Error：" + json);
            e.printStackTrace();
            return null;
        }
        return t1;

    }

    /**
     * 是否json数组
     *
     * @param json json字符串
     * @return boolean true 是 false 否
     */
    public static boolean isJsonArray(String json) {
        return json.startsWith("[");
    }

    /**
     * 序列化json数组
     * @param launch json字符串
     * @param tClass class类
     * @param <T> List<T>集合
     * @return List<T>
     */
    public static <T> List<T> jsonArrayList(String launch, Class<T> tClass) {
        List<T> tList = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(launch);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                T t = new Gson().fromJson(jsonObject.toString(), tClass);
                tList.add(t);
            }
        } catch (JSONException e) {
            Log.e(TAG, "JsonSyntax Error：" + launch);
            e.printStackTrace();
        }
        return tList;
    }

    /**
     * 得到json文件中的内容
     *
     * @param context  上下文
     * @param fileName 资源文件名称
     * @return String
     */
    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        //获得assets资源管理器
        AssetManager assetManager = context.getAssets();
        //使用IO流读取json文件内容
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(assetManager.open(fileName), UTF_8));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
