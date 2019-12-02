package com.skrshop.securitycore.validate;

import cn.hutool.core.collection.CollectionUtil;
import com.skrshop.securitycore.properties.SkrShopSecurityCenterProperties;
import com.skrshop.securitycore.security.SecurityConstants;
import com.skrshop.securitycore.validate.code.ValidateCodeException;
import com.skrshop.securitycore.validate.code.ValidateCodeProcessorHolder;
import com.skrshop.securitycore.validate.code.ValidateCodeType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 保证过滤器每次只会被调用一次
 * 在都启动后装配urls的配置值
 */
@Component("validateCodeFilter")
@Slf4j
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {
    /**
     * 验证码校验失败处理器
     */
    @Resource
    private AuthenticationFailureHandler authenticationFailureHandler;
    /**
     * 安全信息配置
     */
    @Resource
    private SkrShopSecurityCenterProperties securityProperties;
    /**
     * 校验码处理器
     */
    @Resource
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;
    /**
     * 存放所有需要校验验证码的url
     */
    private Map<String, ValidateCodeType> urlMap = new ConcurrentHashMap<>();

    /**
     * 路径匹配
     */
    private AntPathMatcher antPathMatcher = new AntPathMatcher();


    /**
     * 初始化配置需要验证拦截的url
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        updateUrl(securityProperties.getCode().getSms().getUrl(), ValidateCodeType.SMS);
    }


    /**
     * 将系统中的配置需要校验验证码的url根据校验类型放入map中
     *
     * @param urlString 需要校验的url
     * @param type      校验类型
     */
    private void updateUrl(List<String> urlString, ValidateCodeType type) {
        Map<String, ValidateCodeType> newUrlMap = new ConcurrentHashMap<>();
        newUrlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, ValidateCodeType.SMS);
        if (CollectionUtil.isNotEmpty(urlString)) {
            urlString.parallelStream().forEach(item -> newUrlMap.put(item, type));
        }
        urlMap = newUrlMap;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //获取请求的类型
        ValidateCodeType type = getValidateType(request);
        if (null != type) {
            log.info("请求校验中的类型为{}", type.name());
            try {
                validateCodeProcessorHolder.findValidateCodeProcessor(type)
                        .validate(new ServletWebRequest(request, response));
            } catch (ValidateCodeException e) {
                logger.error("认证失败，具体错误信息为e={}", e);
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * 获取验证类型
     */
    private ValidateCodeType getValidateType(HttpServletRequest request) {
        AtomicReference<ValidateCodeType> validateType = new AtomicReference<>();
        //这里设置提交登录表单信息的请求URL，如果是需要校验的返回校验的类型
        urlMap.keySet()
                .stream()
                .filter(url -> antPathMatcher.match(url, request.getRequestURI()))
                .findFirst()
                .ifPresent(url -> validateType.set(urlMap.get(url)));

        return validateType.get();
    }

}
