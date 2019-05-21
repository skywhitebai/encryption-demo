package com.sky.encryption;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author baixueping
 * @description base64加密
 * @date 2019/5/21 20:26
 */
public class Base64Test {
    /**
     * base64加密
     *
     * @param str
     * @return
     */
    public static String base64Encode(String str) {
        if (str == null) {
            return null;
        }
        String strBase64 = "";
        try {
            BASE64Encoder enc = new BASE64Encoder();
            strBase64 = enc.encode(str.getBytes());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return strBase64;
    }

    /**
     * base64解密
     *
     * @param str
     * @return
     */
    public static String base64decode(String str) {
        if (str == null) {
            return null;
        }
        byte[] bytes = null;
        try {
            BASE64Decoder dec = new BASE64Decoder();
            bytes = dec.decodeBuffer(str);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new String(bytes);
    }

    public static void main(String[] args) {
        String str = "123456abcdef";
        String encodeStr = base64Encode(str);
        System.out.println(encodeStr);
        String decodeStr = base64decode(encodeStr);
        System.out.println(decodeStr);
    }
}
