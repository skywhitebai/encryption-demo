package com.sky.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author baixueping
 * @description MD5消息摘要算法（英语：MD5 Message-Digest Algorithm）
 * @date 2019/5/21 20:15
 */
public class Md5 {
    /**
     * 计算字符串的 MD5
     *
     * @param text 原文
     * @return 密文
     */
    public static String md5Encode(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(text.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                String hexString = Integer.toHexString(b & 0xFF);
                if (hexString.length() == 1) {
                    hexString = "0" + hexString;
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
        }
        return null;
    }

    public static void main(String[] args) {
        String str="123456abcdef";
        System.out.println(md5Encode(str));
    }
}
