package com.skrshop.oauthcenter.util;

import com.skrshop.oauthcenter.model.JwtInfo;
import com.skrshop.oauthcenter.model.po.StaffInfo;
import io.jsonwebtoken.*;
import org.apache.commons.io.IOUtils;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JwtToken class
 *
 * @author tangbin
 * @date 2018年7月7日12:28:13
 */
public class JwtUtils {


    /**
     * 读取资源文件
     *
     * @param fileName 文件的名称
     * @return
     */
    public static String readResourceKey(String fileName) {
        String key = null;
        try {
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            assert inputStream != null;
            key = IOUtils.toString(inputStream, String.valueOf(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return key;
    }

    /**
     * 构建token
     *
     * @param user      用户对象
     * @param ttlMillis 过期的时间-毫秒
     * @return
     * @throws Exception
     */
    public static <T extends StaffInfo> String buildJwtRS256(T user, long ttlMillis) throws Exception {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.RS256;
        // 读取私钥
        String key = readResourceKey("secret/rsa_private_key_pkcs8.pem");

        // 生成签名密钥
        byte[] keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        // 生成JWT的时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        //创建payload的私有声明（根据特定的业务需要添加，如果要拿这个做验证，一般是需要和jwt的接收方提前沟通好验证方式的）
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("userid", user.getStaffId());
        claims.put("username", user.getName());
        // 没配置过期时间 默认是30分钟
        ttlMillis = ttlMillis >= 0 ? ttlMillis : 30 * 60 * 1000;
        // 当前时间加上过期的秒数
        long expMillis = nowMillis + ttlMillis;
        Date exp = new Date(expMillis);
        // 设置过期时间
        claims.put(JwtInfo.EXPTIME, exp);

        // 生成jwt文件
        JwtBuilder builder = Jwts.builder()
                // 这里其实就是new一个JwtBuilder，设置jwt的body
                // 如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setClaims(claims)
                .setHeaderParam("typ", "JWT")
                .setIssuedAt(now)
                .setSubject(JackSonUtil.encode(user))
                .signWith(signatureAlgorithm, privateKey);


        return builder.compact();
    }

    /**
     * 解密Jwt内容
     *
     * @param jwt
     * @return
     */
    public static JwtInfo parseJwtRS256(String jwt) {
        Claims claims = null;
        try {
            // 读取公钥
            String key = readResourceKey("secret/rsa_public_key.pem");
            // 生成签名公钥
            byte[] keyBytes = (new BASE64Decoder()).decodeBuffer(key);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(keySpec);
            claims = Jwts.parser()
                    .setSigningKey(publicKey)
                    .parseClaimsJws(jwt).getBody();
        } catch (ExpiredJwtException e) {
            System.out.println(e.getClaims().toString());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return new JwtInfo(claims);
    }

    public static void main(String[] args) throws Exception {
        StaffInfo staffInfo = new StaffInfo();
        staffInfo.setStaffId(100L);
        staffInfo.setName("yh");
        String s = buildJwtRS256(staffInfo, 10000L);
        System.out.println(s);
        System.out.println(parseJwtRS256(s).getExp());


    }
}
