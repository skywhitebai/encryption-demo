package com.sky.encryption;

import com.sky.util.Base64Util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**
 * @author baixueping
 * @description des加密
 * @date 2019/5/21 20:37
 */
public class DesTest {
    /* 加密使用的 key */
    private final static byte[] KEY_BYTES = "Vp6fhlFXKpGW8k6QPRg7Q6Jb7HyAhRi6MIhJ2YtGD3Zl26eTthJTj5PnIjXH5EI4".getBytes();

    /**
     * DES 加密
     *
     * @param content 待加密内容
     * @param key     加密的密钥
     * @return 加密后的字节数组
     */
    public static byte[] encryptDES(byte[] content, byte[] key) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKey);
            // DES 是加密方式, EBC 是工作模式, PKCS5Padding 是填充模式
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, random);
            return cipher.doFinal(content);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * DES 解密
     *
     * @param content 待解密内容
     * @param key     解密的密钥
     * @return 解密的数据
     */
    public static byte[] decryptDES(byte[] content, byte[] key) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKey);
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, random);
            return cipher.doFinal(content);
        } catch (Exception e) {
        }
        return null;
    }

    public static void main(String[] args) {
        String str = "123456abcdef";
        byte[] encodeBytes = encryptDES(str.getBytes(),KEY_BYTES);
        String encodeStr = new String(encodeBytes);
        System.out.println(encodeBytes);
        System.out.println(encodeStr);
        System.out.println(Base64Util.byteToBase64(encodeBytes));
        byte[] decodeBytes = decryptDES(encodeBytes,KEY_BYTES);
        String decodeStr = new String(decodeBytes);
        System.out.println(decodeBytes);
        System.out.println(decodeStr);
    }
}
