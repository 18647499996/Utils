package com.liudonghan.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.nfc.Tag;
import android.nfc.tech.MifareClassic;
import android.util.Log;

import java.io.IOException;

/**
 * Description：
 *
 * @author Created by: Li_Min
 * Time:1/6/23
 */
public class ADNFCManagerUtils {

    private int bIndex;// 共多少个扇区
    private int bCount;// 每个扇区的块数

    private static volatile ADNFCManagerUtils instance = null;

    private ADNFCManagerUtils() {
    }

    public static ADNFCManagerUtils getInstance() {
        //single chcekout
        if (null == instance) {
            synchronized (ADNFCManagerUtils.class) {
                // double checkout
                if (null == instance) {
                    instance = new ADNFCManagerUtils();
                }
            }
        }
        return instance;
    }


    /**
     * 读取NFC数据
     *
     * @param context     上下文
     * @param detectedTag 获取TagNFC对象
     */
    public String readNFC(Context context, Tag detectedTag) {
        String readMsg = readNFCTag(context, detectedTag);
        char e = (char) 3;
        int eIndex = readMsg.indexOf(e);
        if (eIndex > 0) {
            readMsg = readMsg.substring(0, eIndex);
        }
        return readMsg;
    }

    /**
     * 写入NFC数据
     *
     * @param tag   NFC Tag 数据
     * @param strSP 写入数据
     */
    public boolean writeNFC(Tag tag, String strSP) {
        return writeNFC(tag, strSP);
    }

    // //读取数据
    @SuppressLint("NewApi")
    public String readNFCTag(Context context, Tag tag) {
        MifareClassic mfc = MifareClassic.get(tag);
        for (String tech : tag.getTechList()) {
            System.out.println(tech);// 显示设备支持技术
        }
        boolean auth = false;
        // 读取TAG

        try {
            StringBuffer metaInfo = new StringBuffer();
            // spMsg = new StringBuffer();
            // String metaInfo = "";
            // Enable I/O operations to the tag from this TagTechnology object.
            mfc.connect();
            int type = mfc.getType();// 获取TAG的类型
            int sectorCount = mfc.getSectorCount();// 获取TAG中包含的扇区数
            String typeS = "";
            switch (type) {
                case MifareClassic.TYPE_CLASSIC:
                    typeS = "TYPE_CLASSIC";
                    break;
                case MifareClassic.TYPE_PLUS:
                    typeS = "TYPE_PLUS";
                    break;
                case MifareClassic.TYPE_PRO:
                    typeS = "TYPE_PRO";
                    break;
                case MifareClassic.TYPE_UNKNOWN:
                    typeS = "TYPE_UNKNOWN";
                    break;
            }
            metaInfo.append("  卡片类型：" + typeS + "\n共" + sectorCount + "个扇区\n共"
                    + mfc.getBlockCount() + "个块\n存储空间: " + mfc.getSize()
                    + "B\n");
            // 从第1个扇区开始读取，第0个扇区不读
            byte[] msgByte = new byte[31 * 3 * 16 + 8 * 16 * 15];
            int cnt = 0;
            for (int j = 1; j < sectorCount; j++) {
                auth = mfc.authenticateSectorWithKeyA(j,
                        MifareClassic.KEY_DEFAULT);// 逐个获取密码
                if (auth) {
                    // metaInfo.append("Sector " + j + ":验证成功\n");
                    // metaInfo += "Sector " + j + ":验证成功\n";
                    // 读取扇区中的块
                    bCount = mfc.getBlockCountInSector(j);
                    bIndex = mfc.sectorToBlock(j);
                    for (int i = 0; i < bCount; i++) {
                        byte[] data = mfc.readBlock(bIndex);
                        if (j > 31) {
                            // 大于31扇区之后只读取前0-14个块，第15块为密码块
                            if (i != 15) {
                                metaInfo.append("Block "
                                        + bIndex
                                        + " : "
                                        + byteArrayToHexString(data)
                                        + "\n");
                                int offset = cnt * 16;
                                System.arraycopy(data, 0, msgByte, offset,
                                        data.length);
                                cnt++;
                            }
                        } else {
                            // 小于31扇区的只读取前0-2个块，第3个块为密码块
                            if (i != 3) {
                                metaInfo.append("Block "
                                        + bIndex
                                        + " : "
                                        + byteArrayToHexString(data)
                                        + "\n");
                                int offset = cnt * 16;
                                System.arraycopy(data, 0, msgByte, offset,
                                        data.length);
                                cnt++;
                            }
                        }
                        bIndex++;
                    }
                } else {
                    metaInfo.append("Sector " + j + ":验证失败\n");
                    // metaInfo += "Sector " + j + ":验证失败\n";
                }
            }
            String spMsg = byteArrayToStr(msgByte);
            // return metaInfo;
            return spMsg;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (mfc != null) {
                try {
                    mfc.close();
                } catch (IOException e) {
                }
            }
        }
        return null;

    }


    @SuppressLint("NewApi")
    public boolean writeTag(Tag tag, String strSP) {
        MifareClassic mfc = MifareClassic.get(tag);

        try {
            mfc.connect();
            Log.i("write", "----connect-------------");
            boolean CodeAuth = false;
            int sectorCount = 40;
            byte[] mByte = getMsgByte(strSP);// 将需要存储的字符串转换为byte数组
            int sourceOffset = 0;
            boolean blockFlg = false;

            for (int j = 1; j < sectorCount; j++) {
                CodeAuth = mfc.authenticateSectorWithKeyA(j,
                        MifareClassic.KEY_DEFAULT);
                if (CodeAuth) {
                    bCount = mfc.getBlockCountInSector(j);
                    bIndex = mfc.sectorToBlock(j);
                    for (int i = 0; i < bCount; i++) {
                        if (j > 31) {
                            // 大于31扇区之后只读取前0-14个块，第15块为密码块
                            if (i != 15) {
                                int dataLen = 16;
                                if (sourceOffset + 16 > mByte.length) {
                                    dataLen = sourceOffset + 16 - mByte.length;
                                }
                                byte[] data = new byte[dataLen];
                                System.arraycopy(mByte, sourceOffset, data, 0,
                                        dataLen);
                                mfc.writeBlock(bIndex, data);
                                sourceOffset = sourceOffset + dataLen;
                                if (sourceOffset >= mByte.length) {
                                    blockFlg = true;
                                    break;
                                }
                            }
                        } else {
                            // 小于31扇区的只读取前0-2个块，第3个块为密码块
                            if (i != 3) {
                                int dataLen = 16;
                                if (sourceOffset + 16 > mByte.length) {
                                    int arrLen = mByte.length;
                                    dataLen = mByte.length - sourceOffset;
                                }
                                byte[] data = new byte[16];
                                System.arraycopy(mByte, sourceOffset, data, 0,
                                        dataLen);
                                mfc.writeBlock(bIndex, data);
                                sourceOffset = sourceOffset + dataLen;
                                if (sourceOffset >= mByte.length) {
                                    blockFlg = true;
                                    break;
                                }
                            }
                        }
                        bIndex++;
                    }
                    // if(blockFlg)break;
                } else {
                    //Toast.makeText(this, "Sector " + j + ":验证失败", 1).show();
                    break;
                }
            }
            return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        } finally {
            try {
                mfc.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * 转换法2 格式为 ABCD1234 字母大写
     *
     * @param bytesId
     * @return
     */
    public static String byteArrayToHexString(byte[] bytesId) {
        // Byte数组转换为16进制字符串
        // TODO 自动生成的方法存根
        int i, j, in;
        String[] hex = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A",
                "B", "C", "D", "E", "F"};
        String output = "";

        for (j = 0; j < bytesId.length; ++j) {
            in = bytesId[j] & 0xff;
            i = (in >> 4) & 0x0f;
            output += hex[i];
            i = in & 0x0f;
            output += hex[i];
        }
        return output;
    }

    public static String byteArrayToStr(byte[] byteArray) {
        try {
            if (byteArray == null) {
                return null;
            }
            String str = new String(byteArray);
            return str;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将MSG转换为byte数组，长度需要为16的倍数，不满16则补齐#
     *
     * @param msg 写入值
     * @return byte[]
     */
    private byte[] getMsgByte(String msg) {
        byte[] mByte = msg.getBytes();// 将需要存储的字符串转换为byte数组
        byte[] msgByte;
        int Remainder = 16 - mByte.length % 16;
        if (Remainder != 16) {
            // 不等于0的情况需要补足位数
            msgByte = new byte[mByte.length + Remainder];
            for (int i = 0; i < mByte.length; i++) {
                msgByte[i] = mByte[i];
            }
            return msgByte;
        } else {
            return msg.getBytes();
        }
    }
}
