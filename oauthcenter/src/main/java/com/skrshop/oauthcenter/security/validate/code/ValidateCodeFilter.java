package com.skrshop.oauthcenter.security.validate.code;

import com.skrshop.common.error.SkrShopException;
import com.skrshop.oauthcenter.model.AuthResultCode;
import com.skrshop.oauthcenter.security.config.properties.SkrShopAuthorityCenterProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
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
import java.util.*;

@Component
@Slf4j
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    private final String IMAGECODEKEY = "security_imagecode";

    @Resource
    private SkrShopAuthorityCenterProperties skrShopAuthorityCenterProperties;

    @Resource
    private ValueOperations<String, String> valueOperations;

    private Set<String> urls = new HashSet<>(4);

    private AntPathMatcher pathMatcher = new AntPathMatcher();


    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(skrShopAuthorityCenterProperties.getSecurity().getCode().getImage().getUrl(), ",");
        if (Objects.nonNull(configUrls)) {
            urls.addAll(Arrays.asList(configUrls));
        }
        urls.add("/skrshop/oauth/authentication/form");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (skrShopAuthorityCenterProperties.getSecurity().getCode().getImage().getEnable() && urls.stream().anyMatch(itm -> pathMatcher.match(itm, request.getRequestURI()))) {
            log.info("--------匹配到需要验证码接口----{}", request.getRequestURI());
            HttpServletRequest parameterRequestWrapper = new HttpServletRequestWrapper((HttpServletRequest) request);
            //或者使用定义好的SecurityFailHandler异常处理器进行捕获
            if (!validate(parameterRequestWrapper)) {
                throw new SkrShopException(AuthResultCode.VALIDATE_CODE_ERROR);
            }
            filterChain.doFilter(parameterRequestWrapper, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private boolean validate(HttpServletRequest servletWebRequest) throws ServletRequestBindingException {
        String imageCode = Optional.ofNullable(ServletRequestUtils.getStringParameter(servletWebRequest, "imageCode"))
                .orElseThrow(() -> new SkrShopException(AuthResultCode.VALIDATE_CODE_NOT_EXISTS));
        String s = Optional.ofNullable(valueOperations.get(IMAGECODEKEY))
                .orElseThrow(() -> new SkrShopException(AuthResultCode.VALIDATE_CODE_EXPIRE));
        log.info("入参code {},实际值 {}", imageCode, s);
        return imageCode.equals(s);
    }
}
