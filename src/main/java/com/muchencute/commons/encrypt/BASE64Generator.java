package com.muchencute.commons.encrypt;

import org.apache.commons.codec.binary.Base64;

@SuppressWarnings("ALL")
public class BASE64Generator {

    private BASE64Generator() {

    }

    public static String encode(byte[] key) {
        return Base64.encodeBase64String(key);
    }

    public static byte[] decode(String key) {
        return Base64.decodeBase64(key);
    }

}
