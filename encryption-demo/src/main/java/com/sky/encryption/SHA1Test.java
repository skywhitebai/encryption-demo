package com.sky.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author baixueping
 * @description 安全哈希算法（Secure Hash Algorithm）主要适用于数字签名标准 （Digital Signature Standard DSS）里面定义的数字签名算法（Digital Signature Algorithm DSA）
 * @date 2019/6/10 15:01
 */
public class SHA1Test {
    public static String computeSHA1(String text) {
        try {
            MessageDigest sha1 = MessageDigest.getInstance("SHA1");
            byte[] digest = sha1.digest(text.getBytes());
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
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        String str="123456abcdef";
        System.out.println(computeSHA1(str));
    }
}
