package com.skrshop.oauthcenter.model;

import com.skrshop.oauthcenter.model.po.StaffInfo;
import com.skrshop.oauthcenter.util.JackSonUtil;
import io.jsonwebtoken.Claims;
import lombok.Data;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;


@Data
public class JwtInfo {
    public static String EXPTIME = "EXP_TIME";
    private StaffInfo staffInfo;
    private Long exp;

    public JwtInfo(Claims claims) {
        this.staffInfo = Objects.nonNull(claims) ? JackSonUtil.decode(claims.getSubject(), StaffInfo.class) : null;
        Optional.ofNullable(claims).ifPresent(item -> Optional.ofNullable(item.get(EXPTIME, Date.class)).ifPresent(time -> this.exp = time.getTime()));
    }
}
