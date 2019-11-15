package com.skrshop.oauthcenter.security.process;

import com.skrshop.oauthcenter.common.enums.LoginTypeEnum;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;

import static org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY;
import static org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY;

@Component
public class NamePwdLoginProcessor implements LoginProcessor {
    @Override
    public LoginTypeEnum getLoginTypeEnum() {

        return LoginTypeEnum.NORMAL_LOGIN;
    }

    @Override
    public String obtainUsername(ServletRequest request) {
        return request.getParameter(SPRING_SECURITY_FORM_USERNAME_KEY);
    }

    @Override
    public String obtainPassword(ServletRequest request) {
        return request.getParameter(SPRING_SECURITY_FORM_PASSWORD_KEY);
    }

    @Override
    public Boolean match(ServletRequest request) {
        return null;
    }

}
