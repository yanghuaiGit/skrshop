package com.skrshop.oauthcenter.security.process;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY;
import static org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY;

@Component
public class PreLoginFilter extends OncePerRequestFilter {

    @Resource
    private ValueOperations<String, String> valueOperations;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (StringUtils.equals("/authentication/form", request.getRequestURI()) &&
                StringUtils.equalsAnyIgnoreCase(request.getMethod(), "post")) {
            HttpServletRequest parameterRequestWrapper = new HttpServletRequestWrapper((HttpServletRequest) request);
            validate(parameterRequestWrapper);


            String username = request.getParameter(SPRING_SECURITY_FORM_USERNAME_KEY);

            String password = request.getParameter(SPRING_SECURITY_FORM_PASSWORD_KEY);

            parameterRequestWrapper.setAttribute(SPRING_SECURITY_FORM_USERNAME_KEY, username);
            parameterRequestWrapper.setAttribute(SPRING_SECURITY_FORM_PASSWORD_KEY, password);
            filterChain.doFilter(parameterRequestWrapper, response);

        } else {
            filterChain.doFilter(request, response);
        }
    }

    private void validate(HttpServletRequest servletWebRequest) throws ServletRequestBindingException {
        String imageCode = ServletRequestUtils.getStringParameter(servletWebRequest, "imageCode");
        String s = valueOperations.get(servletWebRequest.getParameter("phone"));
    }
}
