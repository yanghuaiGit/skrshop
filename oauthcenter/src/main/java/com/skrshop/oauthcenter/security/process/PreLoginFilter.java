package com.skrshop.oauthcenter.security.process;

import com.skrshop.common.error.ServiceException;
import com.skrshop.oauthcenter.model.AuthResultCode;
import com.skrshop.oauthcenter.security.login.LoginManager;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

import static org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY;
import static org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY;

@Component
public class PreLoginFilter extends GenericFilterBean {

    @Resource
    private LoginManager loginManager;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest parameterRequestWrapper = new HttpServletRequestWrapper((HttpServletRequest) request);
        LoginProcessor loginProcessor = loginManager.getLoginProcessors()
                .stream()
                .filter(item -> item.match(request))
                .findFirst()
                .orElseThrow(() -> new ServiceException(AuthResultCode.LOGIN_TYPE_NOT_SUPPORT));

        String username = loginProcessor.obtainUsername(request);

        String password = loginProcessor.obtainPassword(request);


        parameterRequestWrapper.setAttribute(SPRING_SECURITY_FORM_USERNAME_KEY, username);
        parameterRequestWrapper.setAttribute(SPRING_SECURITY_FORM_PASSWORD_KEY, password);


        chain.doFilter(parameterRequestWrapper, response);


    }
}
