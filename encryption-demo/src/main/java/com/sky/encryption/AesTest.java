package com.sky.encryption;

import com.sky.util.Base64Util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author baixueping
 * @description 高级加密标准（英语：Advanced Encryption Standard，缩写：AES） DES 的增强版，比 DES 的加密强度更高。
 * @date 2019/5/21 20:41
 */
public class AesTest {
    /* 加密使用的 key */
    private static final String AES_KEY = "KUbHwTqBy6TBQ2gN";
    /* 加密使用的 IV */
    private static final String AES_IV = "pIbF6GR3XEN1PG05";

    /**
     * AES 加密
     *
     * @param content 待解密内容
     * @param key     密钥
     * @return 解密的数据
     */
    public static byte[] encryptAES(byte[] content, byte[] key) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
            // AES 是加密方式, CBC 是工作模式, PKCS5Padding 是填充模式
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            // IV 是初始向量，可以增强密码的强度
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(AES_IV.getBytes()));
            return cipher.doFinal(content);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * AES 解密
     *
     * @param content 待解密内容
     * @param key     密钥
     * @return 解密的数据
     */
    public static byte[] decryptAES(byte[] content, byte[] key) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(AES_IV.getBytes()));
            return cipher.doFinal(content);
        } catch (Exception e) {
        }
        return null;
    }

    public static void main(String[] args) {
        String str = "123456abcdef";
        byte[] encodeBytes = encryptAES(str.getBytes(), AES_KEY.getBytes());
        String encodeStr = new String(encodeBytes);
        System.out.println(encodeBytes);
        System.out.println(encodeStr);
        System.out.println(Base64Util.byteToBase64(encodeBytes));
        byte[] decodeBytes = decryptAES(encodeBytes, AES_KEY.getBytes());
        String decodeStr = new String(decodeBytes);
        System.out.println(decodeBytes);
        System.out.println(decodeStr);
    }
}
