package com.skrshop.securitycore.security;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface RabcService {
    boolean hasPermission(HttpServletRequest httpServletRequest, Authentication authentication);
}
