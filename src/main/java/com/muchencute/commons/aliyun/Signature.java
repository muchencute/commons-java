package com.muchencute.commons.aliyun;

import com.muchencute.commons.encrypt.HMACGenerator;
import org.apache.commons.lang3.StringUtils;

import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("ALL")
public class Signature {

    private Signature() {

    }

    public static String signature(String url, String appSecret) {
        try {
            String query = new URL(url).getQuery();
            String[] fields = query.split("&");
            for (int i = 0; i < fields.length; i++) {
                String[] keyValue = fields[i].split("=");
                fields[i] = String.format("%s=%s",
                        URLEncoder.encode(keyValue[0], "UTF-8"),
                        URLEncoder.encode(keyValue[1], "UTF-8"));
            }
            List<String> list = Arrays.asList(fields);
            Collections.sort(list);
            String stringToSign = String.format("GET&%%2F&%s", URLEncoder.encode(StringUtils.join(list, "&"), "UTF-8"));
            return HMACGenerator.sha1BASE64(stringToSign, appSecret + "&");
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
