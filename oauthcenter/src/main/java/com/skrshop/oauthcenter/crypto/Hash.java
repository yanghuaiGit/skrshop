package com.skrshop.oauthcenter.crypto;


import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * sha256_HMAC加密
 * 采用散列函数（MD5 or 或SHA）与共享密钥一起使用的消息身份验证机制。
 */
public class Hash {
    public static String encode(String key, String data) throws Exception {
        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
        sha256_HMAC.init(secret_key);

        return Hex.toHexString(sha256_HMAC.doFinal(data.getBytes("UTF-8")));
    }
}
