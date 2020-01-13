package com.skrshop.oauthcenter.security.rabc;

import com.skrshop.securitycore.security.RabcService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Component("rabcService")
public class RabcServiceImpl implements RabcService {
    @Override
    public boolean hasPermission(HttpServletRequest httpServletRequest, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            String userName = ((UserDetails) principal).getUsername();

        //数据库获取到用户拥有的权限所有url 进行判断就行
        }
        return false;
    }
}
