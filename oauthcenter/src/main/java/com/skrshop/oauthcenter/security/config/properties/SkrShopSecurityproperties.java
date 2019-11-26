package com.skrshop.oauthcenter.security.config.properties;

import com.skrshop.oauthcenter.config.LoginTypeEnum;
import lombok.Data;

@Data
public class SkrShopSecurityproperties {

    private String loginpage = "/login.html";

    private LoginTypeEnum loginTypeEnum;

    private int rememberMeSeconds = 3600;

    private ValidateCodeProperties code = new ValidateCodeProperties();
}
