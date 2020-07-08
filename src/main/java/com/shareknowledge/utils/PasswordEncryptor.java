package com.shareknowledge.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryptor {

    public static final String encryptMethod = "SHA-1";
    private static final String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    private String salt;
    private String raw;
    private String result;

    public PasswordEncryptor() {
        this.raw = "";
        this.salt = "";
        this.result = "";
    }

    public PasswordEncryptor(String raw) {
        this();
        this.raw = raw;
    }

    /**
     * 字节数组加密
     * @param data 需要加密的字节数组
     * @return 加密之后的字节数组
     */
    public static byte[] encrypt(byte[] data) {
        // 创建具有指定算法名称的信息摘要
        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance(encryptMethod);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("加密算法名称错误!");
            e.printStackTrace();
        }
        // 使用指定的字节数组对摘要进行最后更新
        assert sha != null;
        sha.update(data);
        // 完成摘要计算并返回
        return sha.digest();
    }

    /**
     * 字符串加密
     * @param data 需要加密的字符串
     * @return 加密之后的字符串
     */
    public static String encrypt(String data) {
        // 验证传入的字符串
        if (data == null || data.equals("")) {
            return "";
        }
        byte[] bytes = PasswordEncryptor.encrypt(data.getBytes());
        // 将得到的字节数组变成字符串返回
        return PasswordEncryptor.byteArrayToHexString(bytes);
    }

    /**
     * 将一个字节转化成十六进制形式的字符串
     * @param b 字节数组
     * @return 字符串
     */
    private static String byteToHexString(byte b) {
        int ret = b;
        if (ret < 0) {
            ret += 256;
        }
        int m = ret / 16;
        int n = ret % 16;
        return PasswordEncryptor.hexDigits[m] + PasswordEncryptor.hexDigits[n];
    }

    /**
     * 转换字节数组为十六进制字符串
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    private static String byteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(byteToHexString(aByte));
        }
        return sb.toString();
    }

    public void encrypt() {
        this.result = PasswordEncryptor.encrypt(this.raw + ":" + this.salt);
    }


    public String getSalt() {
        return this.salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getRaw() {
        return this.raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }

    public String getResult() {
        return this.result;
    }

}
