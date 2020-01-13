package com.skrshop.oauthcenter.security.userdetail;

import org.springframework.security.core.userdetails.UserDetailsService;

public abstract class UserServiceSupport  implements UserDetailsService {
     public abstract boolean support(String userName);
}
