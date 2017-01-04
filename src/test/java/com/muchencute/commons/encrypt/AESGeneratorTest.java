package com.muchencute.commons.encrypt;

import junit.framework.TestCase;
import org.json.JSONObject;

public class AESGeneratorTest extends TestCase {

    private JSONObject mJSONObject;

    public void setUp() throws Exception {
        super.setUp();
        mJSONObject = new JSONObject();
        mJSONObject.put("transId", "20150605111024773643");
        mJSONObject.put("userCode", "testuser");
        mJSONObject.put("channelOrderId", "这里有中文");
        mJSONObject.put("phoneNum", "13810581662");
        mJSONObject.put("cardCode", "cm_00_10m");
        mJSONObject.put("openType", 1);
    }

    public void testEncrypt() throws Exception {
//        String jsonString = mJSONObject.toString();
        String jsonString = "{\"region\":\"全国\",\"amount\":20,\"accountName\":\"test\",\"cellphone\":\"18620398354\",\"transId\":\"20170104121203260591\",\"channelOrderId\":\"20170104121203260591\"}";
        System.out.println(jsonString);
        System.out.println(AESGenerator.encrypt(jsonString, "1234567890ABCDEF"));
    }

    public void testDecrypt() throws Exception {
        System.out.println(AESGenerator.decrypt("EC54FF00041CFD0FE9EF6A2E27730276D9341C372EC0AD86D1180A8608EA3C1518A5559E6B726AA793E01345E7DC56BAC156AD9282D6F05AA1952882C6EDA8D28691DCAD82497F63F0DB57C3D8DBD5EF8D956E26E9D628D6678EB946761CE0F25BB4038F1D3F406221BA62DE2016D076512CE28C9BFE4734E95CB905FDBE20567D673557FD83E5E813BB786FE20B49BF516A36BE7D616056F066130AC2E778E1", "1234567890ABCDEF"));
    }

}