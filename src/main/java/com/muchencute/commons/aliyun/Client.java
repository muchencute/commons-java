package com.muchencute.commons.aliyun;

import com.muchencute.commons.date.DateFormatter;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.UUID;

@SuppressWarnings("ALL")
public class Client {

    private static Client instance = new Client();
    private String mAppKey = "";
    private String mAppSecret = "";

    private Client() {

    }

    public static Client getInstance(String appKey, String appSecret) {
        instance.setAppKey(appKey);
        instance.setAppSecret(appSecret);
        return instance;
    }

    public static Client getInstance() {
        return instance;
    }

    private void setAppKey(String appKey) {
        mAppKey = appKey;
    }

    private void setAppSecret(String appSecret) {
        mAppSecret = appSecret;
    }

    public JSONObject stsAssumeRole(String roleArn, String roleSessionName, String durationSeconds) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://sts.aliyuncs.com/?SignatureVersion=1.0");
        stringBuilder.append("&Format=JSON");
        stringBuilder.append("&Timestamp=").append(DateFormatter.getUTCDateTime());
        stringBuilder.append("&RoleArn=").append(roleArn);
        stringBuilder.append("&RoleSessionName=").append(roleSessionName);
        stringBuilder.append("&AccessKeyId=").append(mAppKey);
        stringBuilder.append("&SignatureMethod=HMAC-SHA1");
        stringBuilder.append("&Version=2015-04-01");
        stringBuilder.append("&Action=AssumeRole");
        stringBuilder.append("&SignatureNonce=").append(UUID.randomUUID());
        stringBuilder.append("&DurationSeconds=").append(durationSeconds);

        String signature = Signature.signature(stringBuilder.toString(), mAppSecret);
        if (signature == null) {
            return null;
        }

        String url = stringBuilder.append("&Signature=").append(URLEncoder.encode(signature, "UTF-8")).toString();

        HttpGet httpGet = new HttpGet(url);
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                @Override
                public String handleResponse(HttpResponse response) throws IOException {
                    int status = response.getStatusLine().getStatusCode();
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                }
            };

            String responseBody = httpClient.execute(httpGet, responseHandler);
            return new JSONObject(responseBody);
        }
    }

}
