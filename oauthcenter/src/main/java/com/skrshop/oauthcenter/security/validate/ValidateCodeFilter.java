package com.skrshop.oauthcenter.security.validate;

import com.skrshop.common.error.CommonResultCode;
import com.skrshop.common.error.SkrShopException;
import com.skrshop.oauthcenter.model.AuthResultCode;
import lombok.extern.slf4j.Slf4j;
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
import java.util.Optional;

import static org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY;
import static org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY;

@Component
@Slf4j
public class ValidateCodeFilter extends OncePerRequestFilter {

    private final String IMAGECODEKEY = "security_imagecode";

    @Resource
    private ValueOperations<String, String> valueOperations;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (StringUtils.equals("/skrshop/oauth/authentication/form", request.getRequestURI()) &&
                StringUtils.equalsAnyIgnoreCase(request.getMethod(), "post")) {
            HttpServletRequest parameterRequestWrapper = new HttpServletRequestWrapper((HttpServletRequest) request);

            //或者使用定义好的SecurityFailHandler异常处理器进行捕获
            if (!validate(parameterRequestWrapper)) {
                throw new SkrShopException(AuthResultCode.VALIDATE_CODE_ERROR);
            }

            String username = request.getParameter(SPRING_SECURITY_FORM_USERNAME_KEY);

            String password = request.getParameter(SPRING_SECURITY_FORM_PASSWORD_KEY);

            parameterRequestWrapper.setAttribute(SPRING_SECURITY_FORM_USERNAME_KEY, username);
            parameterRequestWrapper.setAttribute(SPRING_SECURITY_FORM_PASSWORD_KEY, password);
            filterChain.doFilter(parameterRequestWrapper, response);

        } else {
            filterChain.doFilter(request, response);
        }
    }

    private boolean validate(HttpServletRequest servletWebRequest) throws ServletRequestBindingException {
        String imageCode = Optional.ofNullable(ServletRequestUtils.getStringParameter(servletWebRequest, "imageCode"))
                .orElseThrow(() -> new SkrShopException(AuthResultCode.VALIDATE_CODE_NOT_EXISTS));
        String s = Optional.ofNullable(valueOperations.get(IMAGECODEKEY +
                Optional.ofNullable(servletWebRequest.getParameter("phone"))
                        .orElseThrow(() -> new SkrShopException(CommonResultCode.PARAM_MISS))))
                .orElseThrow(() -> new SkrShopException(AuthResultCode.VALIDATE_CODE_EXPIRE));
        log.info("入参code {},实际值 {}", imageCode, s);
        return imageCode.equals(s);
    }
}
