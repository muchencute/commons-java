package com.muchencute.commons.encrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

@SuppressWarnings("ALL")
public class AESGenerator {

    private AESGenerator() {
    }

    public static String decrypt(String content, String password) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKeySpec secretKeySpec = new SecretKeySpec(password.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] encrypted = cipher.doFinal(MD5Generator.fromHexString(content));
            return new String(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String encrypt(String content, String password) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            int blockSize = cipher.getBlockSize();
            byte[] dataBytes = content.getBytes();
            SecretKeySpec keyspec = new SecretKeySpec(password.getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, keyspec);
            byte[] encrypted = cipher.doFinal(content.getBytes());
            return MD5Generator.toHexString(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}