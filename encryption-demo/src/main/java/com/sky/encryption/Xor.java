package com.sky.encryption;

import com.sky.util.Base64Util;

/**
 * @author baixueping
 * @description 异或加密
 * @date 2019/5/21 20:02
 */
public class Xor {
    // 加密的密钥，构造一定长度的字节数组
    private final static byte[] KEY_BYTES = "Vp6flFpGW86g7hi6MhD3Zl2eThJTjPnIjXE4".getBytes();
    private final static int KEY_LENGTH = KEY_BYTES.length;

    /**
     * 异或运算加密
     *
     * @param input 要加密的内容
     * @return 加密后的数据
     */
    public static byte[] xorEncode(byte[] input) {
        int keyIndex = 0;
        int length = input.length;
        for (int i = 0; i < length; i++) {
            input[i] = (byte) (input[i] ^ KEY_BYTES[(keyIndex++ % KEY_LENGTH)]);
        }
        return input;
    }

    /**
     * 异或运算解密
     *
     * @param input 要解密的内容
     * @return 解密后的数据
     */
    public static byte[] xorDecode(byte[] input) {
        int keyIndex = 0;
        int length = input.length;
        for (int i = 0; i < length; i++) {
            input[i] = (byte) (input[i] ^ KEY_BYTES[(keyIndex++ % KEY_LENGTH)]);
        }
        return input;
    }
    public static void main( String[] args )
    {
        String str="123456abcdef";
        byte[] encodeBytes=xorEncode(str.getBytes());
        System.out.println( encodeBytes );
        System.out.println( new String(encodeBytes));
        System.out.println(Base64Util.byteToBase64(encodeBytes));
        byte[]decodeBytes=xorEncode(encodeBytes);
        System.out.println( decodeBytes );
        System.out.println( new String(decodeBytes));
    }
}
