package com.muchencute.commons.encrypt;

import org.apache.commons.codec.digest.HmacUtils;

public class HMACGenerator {

    private HMACGenerator() {

    }

    public static String sha1BASE64(String source, String key) {
        byte[] data = HmacUtils.hmacSha1(key.getBytes(), source.getBytes());
        return BASE64Generator.encode(data);
    }

}
