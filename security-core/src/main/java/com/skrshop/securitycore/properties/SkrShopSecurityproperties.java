package com.skrshop.securitycore.properties;

import com.skrshop.securitycore.security.SecurityConstants;
import lombok.Data;

@Data
class SkrShopSecurityproperties {

    private String loginpage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;

    private ValidateCodeProperties code = new ValidateCodeProperties();

}
