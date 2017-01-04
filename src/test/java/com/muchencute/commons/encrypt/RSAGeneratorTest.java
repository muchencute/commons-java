package com.muchencute.commons.encrypt;

import com.muchencute.commons.date.DateFormatter;
import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.Map;

public class RSAGeneratorTest extends TestCase {

    private static String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDCwdLm9lwOVRfkTCJkfYdmxcYSygdWUlrIlzoD" +
            "MJ1qs7sV3QzjXfkNGV4MB16WADDsHa50SmWknjWDBCgldGEdf1w8K38jWddutGZ19sA2JSrHGP01" +
            "43birRtL8O0UWrPQpkCFsPIVHjuDePOJRenT44S72OgSgxwi+JMBYE0tdwIDAQAB";

    private static String PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAMLB0ub2XA5VF+RMImR9h2bFxhLK" +
            "B1ZSWsiXOgMwnWqzuxXdDONd+Q0ZXgwHXpYAMOwdrnRKZaSeNYMEKCV0YR1/XDwrfyNZ1260ZnX2" +
            "wDYlKscY/TXjduKtG0vw7RRas9CmQIWw8hUeO4N484lF6dPjhLvY6BKDHCL4kwFgTS13AgMBAAEC" +
            "gYB9FdfLF3fQjL6nlm6QdD8+ts+cyb0kqAg+xUIxQkwYeA8036Cjd3u5+gz5VNJfLQg+3mFpt07J" +
            "CAP+ffD6/WBRXa+GufiLNlp0bO7Sn8t/hJoDlRczFsZ0XX7xvv9l3rc01iNvO+0jSAXr9/HdLjRu" +
            "z9BP6s/rP7f+oq8HBdZKgQJBAPS6qYHe9tU/lg/nHYaVQXeKmcGD+Xr8NSOFP9BftAdv4UK0eBGN" +
            "VCNbSrEnmrF/Wj+86US2UFhOmb8qmI3XMUcCQQDLuf7QdQJp2M78+1JpziybXuFrpgvjAh1+YBGj" +
            "QgD/Ij54TAOzabizlqetzbN2jlouvtjozpYcrFCAC5bLz7pRAkAZhQV8B3YR0qmhSuZnTFUhVBCo" +
            "f0yaqIYaCCEZZ4FsbRw+SndizSwC69gkFQ8qhy8wClEA20oRi1C5AIYb9F6tAkBNAwRmPLUq2gB0" +
            "9XRT9tI58YX5MY2wxK3thddlBaBm0cUXqHSFr+NYUA0W0HmUyX8hyJcqel2YfTL5FGhupxnhAkEA" +
            "1zQbMT+k3yP48YVn8SiA+luiGekbPNAbreix6aT7+4iO/dKEzie6YoBQtHEr0Y/wt48bFebCKp/H" +
            "lUjdzcpDEQ==";

    private static String RAW_STRING = "098F6BCD4621D373CADE4E832627B4F6" +
            DateFormatter.getCustomizedDateTimeFormat("yyyyMMddHHmmssSSS");

    public void testEncryptByPublicKey() throws Exception {
        byte[] encryptedData = RSAGenerator.encryptByPublicKey(RAW_STRING.getBytes(), PUBLIC_KEY);
        String hexedData = MD5Generator.toHexString(encryptedData);
        System.out.println("Encrypted data is : " + hexedData);
        byte[] unHexedData = MD5Generator.fromHexString(hexedData);
        byte[] decryptedData = RSAGenerator.decryptByPrivateKey(unHexedData, PRIVATE_KEY);
        System.out.println("Decrypted data is : " + new String(decryptedData));
    }

    public void testEncryptByPrivateKey() throws Exception {
        byte[] encryptedData = RSAGenerator.encryptByPrivateKey(RAW_STRING.getBytes(), PRIVATE_KEY);
        String hexedData = MD5Generator.toHexString(encryptedData);
        System.out.println("Encrypted data is : " + hexedData);
        byte[] unHexedData = MD5Generator.fromHexString(hexedData);
        byte[] decryptedData = RSAGenerator.decryptByPublicKey(unHexedData, PUBLIC_KEY);
        System.out.println("Decrypted data is : " + new String(decryptedData));
    }

    public void testGetRSAKeys() throws Exception {
        Map<String, String> keys = RSAGenerator.getRSAKeys();
        String publicKey = RSAGenerator.getPublicKey(keys);
        String privateKey = RSAGenerator.getPrivateKey(keys);
        Assert.assertTrue("fail to generate private/public keys", publicKey != null && privateKey != null);
        System.out.println("Public Key is : " + publicKey);
        System.out.println("Private Key is : " + privateKey);
    }

    public void testPrintTestKeys() {
        System.out.println("(Test) Public Key is : " + PUBLIC_KEY);
        System.out.println("(Test) Private Key is : " + PRIVATE_KEY);
    }

    public void testDecrypt() throws Exception {
        byte[] unHexedData = MD5Generator.fromHexString("655D1EC9D936BFD3F3043297C8182DDE0EB9DF43F4FF708759564A2F18BA07413953FFF7EAB0FC992A441A17B23CC5ADB21C4B6EFB77E95906F76C3043F4E2C38715C6C7295ABDE18FD09D0D4F3474E7D1A359F76E5395E8F1EE1CBBD8878F05750E2212A685449AA4FC453B8894633C9428F877926F8614784DEA268549ABF6");
        byte[] decryptedData = RSAGenerator.decryptByPublicKey(unHexedData, PUBLIC_KEY);
        System.out.println(new String(decryptedData));
    }

}