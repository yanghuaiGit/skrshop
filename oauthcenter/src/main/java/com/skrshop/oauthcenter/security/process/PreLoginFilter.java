package com.skrshop.oauthcenter.security.process;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

import static org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY;
import static org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY;

//@Component
public class PreLoginFilter extends GenericFilterBean {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest parameterRequestWrapper = new HttpServletRequestWrapper((HttpServletRequest) request);


        String username = request.getParameter(SPRING_SECURITY_FORM_USERNAME_KEY);

        String password = request.getParameter(SPRING_SECURITY_FORM_PASSWORD_KEY);

        parameterRequestWrapper.setAttribute(SPRING_SECURITY_FORM_USERNAME_KEY, username);
        parameterRequestWrapper.setAttribute(SPRING_SECURITY_FORM_PASSWORD_KEY, password);


        chain.doFilter(parameterRequestWrapper, response);


    }
}
