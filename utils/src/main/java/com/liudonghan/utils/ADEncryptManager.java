package com.liudonghan.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:10/11/23
 */
public class ADEncryptManager {
    private static volatile ADEncryptManager instance = null;

    private ADEncryptManager() {
    }

    public static ADEncryptManager getInstance() {
        //single checkout
        if (null == instance) {
            synchronized (ADEncryptManager.class) {
                // double checkout
                if (null == instance) {
                    instance = new ADEncryptManager();
                }
            }
        }
        return instance;
    }

    /**
     * todo 生成MD5串
     *
     * @param encrypt 字符串
     * @return String
     */
    public String encryptMessageDigestMD5(String encrypt) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] b = md.digest(encrypt.getBytes("utf-8"));
            StringBuilder sb = new StringBuilder();
            for (byte value : b) {
                String s = Integer.toHexString(value & 255);
                if (s.length() == 1) {
                    sb.append("0");
                }
                sb.append(s);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encrypt;
    }
}
