package com.sky.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author baixueping
 * @description base64工具类
 * @date 2019/5/21 20:10
 */
public class Base64Util {
    public static byte[] base64ToByte(String strBase64) {
        if (strBase64 == null) {
            return null;
        }
        byte[] bytes = null;
        try {
            BASE64Decoder dec = new BASE64Decoder();
            bytes = dec.decodeBuffer(strBase64);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bytes;
    }


    public static String byteToBase64(byte[] aryByte) {
        if (aryByte == null) {
            return "";
        }
        String strBase64 = "";
        try {
            BASE64Encoder enc = new BASE64Encoder();
            strBase64 = enc.encode(aryByte);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return strBase64;
    }

}
