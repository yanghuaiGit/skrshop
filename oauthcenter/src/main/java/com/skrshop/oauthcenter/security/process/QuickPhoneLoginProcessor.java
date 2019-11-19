package com.skrshop.oauthcenter.security.process;

import com.skrshop.oauthcenter.common.enums.LoginTypeEnum;
import com.skrshop.oauthcenter.security.userdetail.AuthUserDetail;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import java.util.Optional;

/**
 * 手机验证码快捷登录入口
 */
@Component
public class QuickPhoneLoginProcessor implements LoginProcessor {


    @Override
    public int logintype() {
        return LoginTypeEnum.NORMAL_LOGIN.getType();
    }

    @Override
    public AuthUserDetail getUserDetails(String name, String verify) {
        return null;
    }

    @Override
    public Boolean match(ServletRequest request) {
        return Integer.valueOf(
                Optional.ofNullable(request.getParameter("type"))
                        .orElse(String.valueOf(LoginTypeEnum.NORMAL_LOGIN.getType())))
                .equals(logintype());

    }

}
