package com.liudonghan.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.collection.SimpleArrayMap;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * Description：SharedPreferences管理器
 *
 * @author Created by: Li_Min
 * Time:10/28/23
 */
@SuppressLint("ApplySharedPref")
public class ADSharedPreferencesManager {

    private static SimpleArrayMap<String, ADSharedPreferencesManager> SP_UTILS_MAP = new SimpleArrayMap<>();
    private SharedPreferences sp;

    /**
     * 获取SharedPreferences实例
     *
     * @return {@link ADSharedPreferencesManager}
     */
    public static ADSharedPreferencesManager getInstance() {
        return getInstance("");
    }

    /**
     * todo 获取SharedPreferences实例
     *
     * @param fileName SharedPreferences文件名
     * @return {@link ADSharedPreferencesManager}
     */
    public static ADSharedPreferencesManager getInstance(String fileName) {
        if (isSpace(fileName)) fileName = "AD_SharedPreferencesManager";
        ADSharedPreferencesManager spUtils = SP_UTILS_MAP.get(fileName);
        if (spUtils == null) {
            spUtils = new ADSharedPreferencesManager(fileName);
            SP_UTILS_MAP.put(fileName, spUtils);
        }
        return spUtils;
    }

    private ADSharedPreferencesManager(final String spName) {
        sp = ADApplicationUtils.getApp().getSharedPreferences(spName, Context.MODE_PRIVATE);
    }

    /**
     * todo SharedPreferences中写入String
     *
     * @param key   键
     * @param value 值
     */
    public void put(@NonNull final String key, @NonNull final String value) {
        put(key, value, false);
    }

    /**
     * todo SharedPreferences中写入String
     *
     * @param key      键
     * @param value    值
     * @param isCommit {@code true}: {@link SharedPreferences.Editor#commit()}<br>
     *                 {@code false}: {@link SharedPreferences.Editor#apply()}
     */
    public void put(@NonNull final String key, @NonNull final String value, final boolean isCommit) {
        if (isCommit) {
            sp.edit().putString(key, value).commit();
        } else {
            sp.edit().putString(key, value).apply();
        }
    }

    /**
     * todo SharedPreferences中读取String
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值{@code ""}
     */
    public String getString(@NonNull final String key) {
        return getString(key, "");
    }

    /**
     * todo SharedPreferences中读取String
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public String getString(@NonNull final String key, @NonNull final String defaultValue) {
        return sp.getString(key, defaultValue);
    }

    /**
     * todo SharedPreferences中写入int
     *
     * @param key   键
     * @param value 值
     */
    public void put(@NonNull final String key, final int value) {
        put(key, value, false);
    }

    /**
     * todo SharedPreferences中写入int
     *
     * @param key      键
     * @param value    值
     * @param isCommit {@code true}: {@link SharedPreferences.Editor#commit()}<br>
     *                 {@code false}: {@link SharedPreferences.Editor#apply()}
     */
    public void put(@NonNull final String key, final int value, final boolean isCommit) {
        if (isCommit) {
            sp.edit().putInt(key, value).commit();
        } else {
            sp.edit().putInt(key, value).apply();
        }
    }

    /**
     * todo SharedPreferences中读取int
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值-1
     */
    public int getInt(@NonNull final String key) {
        return getInt(key, -1);
    }

    /**
     * todo SharedPreferences中读取int
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public int getInt(@NonNull final String key, final int defaultValue) {
        return sp.getInt(key, defaultValue);
    }

    /**
     * todo SharedPreferences中写入long
     *
     * @param key   键
     * @param value 值
     */
    public void put(@NonNull final String key, final long value) {
        put(key, value, false);
    }

    /**
     * todo SharedPreferences中写入long
     *
     * @param key      键
     * @param value    值
     * @param isCommit {@code true}: {@link SharedPreferences.Editor#commit()}<br>
     *                 {@code false}: {@link SharedPreferences.Editor#apply()}
     */
    public void put(@NonNull final String key, final long value, final boolean isCommit) {
        if (isCommit) {
            sp.edit().putLong(key, value).commit();
        } else {
            sp.edit().putLong(key, value).apply();
        }
    }

    /**
     * todo SharedPreferences中读取long
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值-1
     */
    public long getLong(@NonNull final String key) {
        return getLong(key, -1L);
    }

    /**
     * todo SharedPreferences中读取long
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public long getLong(@NonNull final String key, final long defaultValue) {
        return sp.getLong(key, defaultValue);
    }

    /**
     * todo SharedPreferences中写入float
     *
     * @param key   键
     * @param value 值
     */
    public void put(@NonNull final String key, final float value) {
        put(key, value, false);
    }

    /**
     * todo SharedPreferences中写入float
     *
     * @param key      键
     * @param value    值
     * @param isCommit {@code true}: {@link SharedPreferences.Editor#commit()}<br>
     *                 {@code false}: {@link SharedPreferences.Editor#apply()}
     */
    public void put(@NonNull final String key, final float value, final boolean isCommit) {
        if (isCommit) {
            sp.edit().putFloat(key, value).commit();
        } else {
            sp.edit().putFloat(key, value).apply();
        }
    }

    /**
     * todo SharedPreferences中读取float
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值-1
     */
    public float getFloat(@NonNull final String key) {
        return getFloat(key, -1f);
    }

    /**
     * todo SharedPreferences中读取float
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public float getFloat(@NonNull final String key, final float defaultValue) {
        return sp.getFloat(key, defaultValue);
    }

    /**
     * todo SharedPreferences中写入boolean
     *
     * @param key   键
     * @param value 值
     */
    public void put(@NonNull final String key, final boolean value) {
        put(key, value, false);
    }

    /**
     * todo SharedPreferences中写入boolean
     *
     * @param key      键
     * @param value    值
     * @param isCommit {@code true}: {@link SharedPreferences.Editor#commit()}<br>
     *                 {@code false}: {@link SharedPreferences.Editor#apply()}
     */
    public void put(@NonNull final String key, final boolean value, final boolean isCommit) {
        if (isCommit) {
            sp.edit().putBoolean(key, value).commit();
        } else {
            sp.edit().putBoolean(key, value).apply();
        }
    }

    /**
     * todo SharedPreferences中读取boolean
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值{@code false}
     */
    public boolean getBoolean(@NonNull final String key) {
        return getBoolean(key, false);
    }

    /**
     * todo SharedPreferences中读取boolean
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public boolean getBoolean(@NonNull final String key, final boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }

    /**
     * todo SharedPreferences中写入String集合
     *
     * @param key    键
     * @param values 值
     */
    public void put(@NonNull final String key, @NonNull final Set<String> values) {
        put(key, values, false);
    }

    /**
     * todo SharedPreferences中写入String集合
     *
     * @param key      键
     * @param values   值
     * @param isCommit {@code true}: {@link SharedPreferences.Editor#commit()}<br>
     *                 {@code false}: {@link SharedPreferences.Editor#apply()}
     */
    public void put(@NonNull final String key, @NonNull final Set<String> values, final boolean isCommit) {
        if (isCommit) {
            sp.edit().putStringSet(key, values).commit();
        } else {
            sp.edit().putStringSet(key, values).apply();
        }
    }

    /**
     * todo SharedPreferences中读取StringSet
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值{@code Collections.<String>emptySet()}
     */
    public Set<String> getStringSet(@NonNull final String key) {
        return getStringSet(key, Collections.<String>emptySet());
    }

    /**
     * todo SharedPreferences中读取StringSet
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public Set<String> getStringSet(@NonNull final String key, @NonNull final Set<String> defaultValue) {
        return sp.getStringSet(key, defaultValue);
    }

    /**
     * todo SharedPreferences中获取所有键值对
     *
     * @return Map对象
     */
    public Map<String, ?> getAll() {
        return sp.getAll();
    }

    /**
     * todo SharedPreferences中是否存在该key
     *
     * @param key 键
     * @return {@code true}: 存在<br>{@code false}: 不存在
     */
    public boolean contains(@NonNull final String key) {
        return sp.contains(key);
    }

    /**
     * todo SharedPreferences中移除该key
     *
     * @param key 键
     */
    public void remove(@NonNull final String key) {
        remove(key, false);
    }

    /**
     * todo SharedPreferences中移除该key
     *
     * @param key      键
     * @param isCommit {@code true}: {@link SharedPreferences.Editor#commit()}<br>
     *                 {@code false}: {@link SharedPreferences.Editor#apply()}
     */
    public void remove(@NonNull final String key, final boolean isCommit) {
        if (isCommit) {
            sp.edit().remove(key).commit();
        } else {
            sp.edit().remove(key).apply();
        }
    }

    /**
     * todo SharedPreferences中清除所有数据
     */
    public void clear() {
        clear(false);
    }

    /**
     * todo SharedPreferences中清除所有数据
     *
     * @param isCommit {@code true}: {@link SharedPreferences.Editor#commit()}<br>
     *                 {@code false}: {@link SharedPreferences.Editor#apply()}
     */
    public void clear(final boolean isCommit) {
        if (isCommit) {
            sp.edit().clear().commit();
        } else {
            sp.edit().clear().apply();
        }
    }

    private static boolean isSpace(final String s) {
        if (s == null) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
