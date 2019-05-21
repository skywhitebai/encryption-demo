package com.sky.encryption;

import com.sky.util.Base64Util;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author baixueping
 * @description RSA算法是一种非对称加密算法
 * @date 2019/5/21 20:44
 */
public class RsaTest {
    public static final String AES = "AES";
    //非对称密钥算法
    public static final String KEY_ALGORITHM = "RSA";
    public static final String ECB_PKCS1_PADDING = "RSA/ECB/PKCS1Padding";

    /**
     * 随机生成 RSA 密钥对
     *
     * @param keyLength 密钥长度, 范围: 512～2048, 一般 1024
     * @return 密钥对
     */
    public static KeyPair generateRSAKeyPair(int keyLength) {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance(KEY_ALGORITHM);
            kpg.initialize(keyLength);
            return kpg.genKeyPair();
        } catch (NoSuchAlgorithmException e) {
        }
        return null;
    }

    /**
     * 公钥加密
     *
     * @param data      原文
     * @param publicKey 公钥
     * @return 加密后的数据
     */
    public static byte[] encryptByPublicKey(byte[] data, byte[] publicKey) {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
        try {
            KeyFactory kf = KeyFactory.getInstance(KEY_ALGORITHM);
            PublicKey keyPublic = kf.generatePublic(keySpec);
            Cipher cipher = Cipher.getInstance(ECB_PKCS1_PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, keyPublic);
            return cipher.doFinal(data);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 私钥加密
     *
     * @param data       待加密数据
     * @param privateKey 密钥
     * @return 加密后的数据
     */
    public static byte[] encryptByPrivateKey(byte[] data, byte[] privateKey) {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKey);
        try {
            KeyFactory kf = KeyFactory.getInstance(KEY_ALGORITHM);
            PrivateKey keyPrivate = kf.generatePrivate(keySpec);
            Cipher cipher = Cipher.getInstance(ECB_PKCS1_PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, keyPrivate);
            return cipher.doFinal(data);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 公钥解密
     *
     * @param data      待解密数据
     * @param publicKey 密钥
     * @return 解密后的数据
     */
    public static byte[] decryptByPublicKey(byte[] data, byte[] publicKey) {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
        try {
            KeyFactory kf = KeyFactory.getInstance(KEY_ALGORITHM);
            PublicKey keyPublic = kf.generatePublic(keySpec);
            Cipher cipher = Cipher.getInstance(ECB_PKCS1_PADDING);
            cipher.init(Cipher.DECRYPT_MODE, keyPublic);
            return cipher.doFinal(data);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 私钥解密
     *
     * @param data  待解密的数据
     * @param privateKey 私钥
     * @return 解密后的数据
     */
    public static byte[] decryptByPrivateKey(byte[] data, byte[] privateKey) {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKey);
        try {
            KeyFactory kf = KeyFactory.getInstance(KEY_ALGORITHM);
            PrivateKey keyPrivate = kf.generatePrivate(keySpec);
            Cipher cipher = Cipher.getInstance(ECB_PKCS1_PADDING);
            cipher.init(Cipher.DECRYPT_MODE, keyPrivate);
            return cipher.doFinal(data);
        } catch (Exception e) {
        }
        return null;
    }

    public static void main(String[] args) {
        String str = "123456abcdef";
        KeyPair keyPair=generateRSAKeyPair(1024);
        byte[] encodeBytes = encryptByPublicKey(str.getBytes(), keyPair.getPublic().getEncoded());
        String encodeStr = new String(encodeBytes);
        System.out.println(encodeBytes);
        System.out.println(encodeStr);
        System.out.println(Base64Util.byteToBase64(encodeBytes));
        byte[] decodeBytes = decryptByPrivateKey(encodeBytes, keyPair.getPrivate().getEncoded());
        String decodeStr = new String(decodeBytes);
        System.out.println(decodeBytes);
        System.out.println(decodeStr);
    }
}
