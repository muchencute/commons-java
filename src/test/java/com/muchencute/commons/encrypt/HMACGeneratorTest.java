package com.muchencute.commons.encrypt;

import junit.framework.TestCase;
import org.apache.commons.lang3.StringUtils;

import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HMACGeneratorTest extends TestCase {

    public void testSha1BASE64() throws Exception {
        String testString = "https://sts.aliyuncs.com/?SignatureVersion=1.0&Format=JSON" +
                "&Timestamp=2015-09-01T05%3A57%3A34Z&RoleArn=acs%3Aram%3A%3A1234567890123%3Arole%2Ffirstrole" +
                "&RoleSessionName=client&AccessKeyId=testid&SignatureMethod=HMAC-SHA1&Version=2015-04-01" +
                "&Action=AssumeRole&SignatureNonce=571f8fb8-506e-11e5-8e12-b8e8563dc8d2";
        URL url = new URL(testString);
        String query = url.getQuery();
        String[] fields = query.split("&");
        List<String> list = Arrays.asList(fields);
        Collections.sort(list);
        String stringToSign = String.format("GET&%%2F&%s", URLEncoder.encode(StringUtils.join(list, "&"), "UTF-8"));
        System.out.println(HMACGenerator.sha1BASE64(stringToSign, "testsecret&"));
    }

}