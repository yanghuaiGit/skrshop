package com.skrshop.oauthcenter.model;

import io.jsonwebtoken.Claims;
import lombok.Data;

import java.util.Date;
import java.util.Optional;


@Data
public class JwtInfo {
    public static String EXPTIME = "EXP_TIME";
    public static String USER_NAME = "USER_NAME";
    public static String USER_ID = "USER_ID";
    private String userName;
    private Long uid;
    private Long exp;
    private Claims claims;

    public JwtInfo(Claims claims) {
        Optional.ofNullable(claims).ifPresent(item -> this.claims = item);
        Optional.ofNullable(claims).ifPresent(item -> Optional.ofNullable(item.get(EXPTIME, Date.class)).ifPresent(time -> this.exp = time.getTime()));
        Optional.ofNullable(claims).ifPresent(item -> Optional.ofNullable(item.get(USER_NAME, String.class)).ifPresent(name -> this.userName = name));
        Optional.ofNullable(claims).ifPresent(item -> Optional.ofNullable(item.get(USER_ID, Long.class)).ifPresent(id -> this.uid = id));
    }
}
