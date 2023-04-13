package com.liudonghan.utils;

import java.io.File;
import java.io.FileInputStream;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:4/4/23
 */
public class ADFileUtils {
    private static volatile ADFileUtils instance = null;

    private ADFileUtils() {
    }

    public static ADFileUtils getInstance() {
        //single checkout
        if (null == instance) {
            synchronized (ADFileUtils.class) {
                // double checkout
                if (null == instance) {
                    instance = new ADFileUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 是否是文件
     *
     * @param path 文件路径
     * @return boolean
     */
    public boolean scannerFile(String path) {
        File file = new File(path);
        return file.exists() && file.length() > 0;
    }
}
