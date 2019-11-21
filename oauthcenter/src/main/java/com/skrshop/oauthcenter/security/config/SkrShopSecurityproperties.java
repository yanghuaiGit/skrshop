package com.skrshop.oauthcenter.security.config;

import com.skrshop.oauthcenter.config.LoginTypeEnum;
import lombok.Data;

@Data
public class SkrShopSecurityproperties {

    private String loginpage;

    private LoginTypeEnum loginTypeEnum;
}
