package com.skrshop.securitycore.validate.code.sms;

import com.skrshop.securitycore.security.SecurityConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yh
 */
@Slf4j
public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public SmsCodeAuthenticationFilter() {
        super(new AntPathRequestMatcher("/authentication/mobile", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        if (!"POST".equals(request.getMethod())) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        String username = obtainPhone(request);

        if (username == null) {
            username = "";
        }

        username = username.trim();

        SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(
                username);

        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }


    private String obtainPhone(HttpServletRequest request) {
        return request.getParameter(SecurityConstants.DEFAULT_MOBILE_AUTH_LOGIN_PARAMETER_NAME);
    }


    private void setDetails(HttpServletRequest request,
                            SmsCodeAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

}
