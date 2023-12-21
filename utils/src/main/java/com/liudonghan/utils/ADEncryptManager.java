package com.liudonghan.utils;


import android.annotation.SuppressLint;

import java.security.InvalidAlgorithmParameterException;
import java.util.Arrays;
import java.util.Base64;

import android.text.TextUtils;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:10/11/23
 */
public class ADEncryptManager {

    private static final String CBCMode = "AES/CBC/PKCS5Padding";//填充方式
    private static final String ECBMode = "AES/ECB/PKCS5Padding";//填充方式

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

    /**
     * todo AES CBC 加密
     *
     * @param message 需要加密的字符串
     * @param key     密匙
     * @param iv      IV，需要和key长度相同
     * @return 返回加密后密文，编码为base64
     */
    @SuppressLint({"NewApi", "GetInstance"})
    public String encryptCBC(String message, String key, String iv) {
        try {
            byte[] content = message.getBytes(StandardCharsets.UTF_8);
            byte[] keyByte = key.getBytes(StandardCharsets.UTF_8);
            keyByte = fillKey(keyByte);
            SecretKeySpec keySpec = new SecretKeySpec(keyByte, "AES");
            byte[] ivByte = iv.getBytes(StandardCharsets.UTF_8);
            ivByte = fillIv(ivByte);
            IvParameterSpec ivSpec = new IvParameterSpec(ivByte);
            Cipher cipher = Cipher.getInstance(CBCMode);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            byte[] data = cipher.doFinal(content);
            return Base64.getEncoder().encodeToString(data);
        } catch (InvalidAlgorithmParameterException e) {
            Log.w("Mac_Liu", "无效的算法参数 :" + e);
        } catch (NoSuchAlgorithmException e) {
            Log.w("Mac_Liu", "没有这样的算法 :" + e);
        } catch (NoSuchPaddingException e) {
            Log.w("Mac_Liu", "没有这样的填充 :" + e);
        } catch (InvalidKeyException e) {
            Log.w("Mac_Liu", "无效的密钥 :" + e);
        } catch (IllegalBlockSizeException e) {
            Log.w("Mac_Liu", "非法块大小 :" + e);
        } catch (BadPaddingException e) {
            Log.w("Mac_Liu", "不良填充 :" + e);
        }
        return null;
    }

    /**
     * todo AES CBC 解密
     *
     * @param messageBase64 密文，base64编码
     * @param key           密匙，和加密时相同
     * @param iv            IV，需要和key长度相同
     * @return 解密后数据
     */
    @SuppressLint({"NewApi", "GetInstance"})
    public String decryptCBC(String messageBase64, String key, String iv) {
        try {
            byte[] messageByte = Base64.getDecoder().decode(messageBase64);
            byte[] keyByte = key.getBytes(StandardCharsets.UTF_8);
            keyByte = fillKey(keyByte);
            SecretKeySpec keySpec = new SecretKeySpec(keyByte, "AES");
            byte[] ivByte = iv.getBytes(StandardCharsets.UTF_8);
            ivByte = fillIv(ivByte);
            IvParameterSpec ivSpec = new IvParameterSpec(ivByte);
            Cipher cipher = Cipher.getInstance(CBCMode);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            byte[] content = cipher.doFinal(messageByte);
            return new String(content, StandardCharsets.UTF_8);
        } catch (InvalidAlgorithmParameterException e) {
            Log.w("Mac_Liu", "无效的算法参数 :" + e);
        } catch (NoSuchAlgorithmException e) {
            Log.w("Mac_Liu", "没有这样的算法 :" + e);
        } catch (NoSuchPaddingException e) {
            Log.w("Mac_Liu", "没有这样的填充 :" + e);
        } catch (InvalidKeyException e) {
            Log.w("Mac_Liu", "无效的密钥 :" + e);
        } catch (IllegalBlockSizeException e) {
            Log.w("Mac_Liu", "非法块大小 :" + e);
        } catch (BadPaddingException e) {
            Log.w("Mac_Liu", "不良填充 :" + e);
        }
        return null;
    }

    /**
     * todo AES ECB 加密
     *
     * @param message 需要加密的字符串
     * @param key     密匙
     * @return 返回加密后密文，编码为base64
     */
    @SuppressLint({"NewApi", "GetInstance"})
    public String encryptECB(String message, String key) {
        try {
            byte[] content = message.getBytes(StandardCharsets.UTF_8);
            byte[] keyByte = key.getBytes(StandardCharsets.UTF_8);
            keyByte = fillKey(keyByte);
            SecretKeySpec keySpec = new SecretKeySpec(keyByte, "AES");
            Cipher cipher = Cipher.getInstance(ECBMode);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] data = cipher.doFinal(content);
            return Base64.getEncoder().encodeToString(data);
        } catch (NoSuchAlgorithmException e) {
            Log.w("Mac_Liu", "没有这样的算法 :" + e);
        } catch (NoSuchPaddingException e) {
            Log.w("Mac_Liu", "没有这样的填充 :" + e);
        } catch (InvalidKeyException e) {
            Log.w("Mac_Liu", "无效的密钥 :" + e);
        } catch (IllegalBlockSizeException e) {
            Log.w("Mac_Liu", "非法块大小 :" + e);
        } catch (BadPaddingException e) {
            Log.w("Mac_Liu", "不良填充 :" + e);
        }
        return null;
    }

    /**
     * todo AES ECB 解密
     *
     * @param messageBase64 密文，base64编码
     * @param key           密匙，和加密时相同
     * @return 解密后数据
     */
    @SuppressLint({"NewApi", "GetInstance"})
    public String decryptECB(String messageBase64, String key) {
        try {
            byte[] messageByte = Base64.getDecoder().decode(messageBase64);
            byte[] keyByte = key.getBytes(StandardCharsets.UTF_8);
            keyByte = fillKey(keyByte);
            SecretKeySpec keySpec = new SecretKeySpec(keyByte, "AES");
            Cipher cipher = Cipher.getInstance(ECBMode);
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] content = cipher.doFinal(messageByte);
            return new String(content, StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException e) {
            Log.w("Mac_Liu", "没有这样的算法 :" + e);
        } catch (NoSuchPaddingException e) {
            Log.w("Mac_Liu", "没有这样的填充 :" + e);
        } catch (InvalidKeyException e) {
            Log.w("Mac_Liu", "无效的密钥 :" + e);
        } catch (IllegalBlockSizeException e) {
            Log.w("Mac_Liu", "非法块大小 :" + e);
        } catch (BadPaddingException e) {
            Log.w("Mac_Liu", "不良填充 :" + e);
        }
        return null;
    }

    /**
     * todo base64编码
     *
     * @param data 编码数据
     * @return String
     */
    @SuppressLint("NewApi")
    public String encryptBase64(String data) {
        if (TextUtils.isEmpty(data)) {
            return "";
        }
        return Base64.getEncoder().encodeToString(data.getBytes());
    }

    /**
     * todo base64解码
     *
     * @param encode 解码字符
     * @return String
     */
    @SuppressLint("NewApi")
    public String decryptBase64(String encode) {
        if (TextUtils.isEmpty(encode)) {
            return "";
        }
        return new String(Base64.getDecoder().decode(encode));
    }


    /**
     * 填充key
     * key的字节长度只能是16位、24位、32位。
     * key长度不足时，这里将以0x00填充，超出部分将被忽略。
     *
     * @return 填充后的key
     */
    private byte[] fillKey(byte[] keyByte) {
        int length = keyByte.length;
        int len;
        if (length == 16 || length == 24 || length == 32) {
            return keyByte;
        } else if (length < 16) {
            len = 16;
        } else if (length < 24) {
            len = 24;
        } else {
            len = 32;
        }
        byte[] newKeyByte = new byte[len];
        System.arraycopy(keyByte, 0, newKeyByte, 0, length < len ? length : len);
        return newKeyByte;
    }

    /**
     * 填充iv
     * iv的字节长度只能是16位。
     * iv长度不足时，这里将以0x00填充，超出部分将被忽略。
     *
     * @return 填充后的iv
     */
    private byte[] fillIv(byte[] ivByte) {
        int length = ivByte.length;
        int len;
        if (length == 16) {
            return ivByte;
        } else {
            len = 16;
        }
        byte[] newIvByte = new byte[len];
        System.arraycopy(ivByte, 0, newIvByte, 0, length < len ? length : len);
        return newIvByte;
    }

}
