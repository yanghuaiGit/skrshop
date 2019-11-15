package com.skrshop.oauthcenter.model;

import com.skrshop.common.enums.AccountStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

public class AuthUserDetail implements UserDetails {
    private String password;
    private String userName;
    private Integer status;
    //使用tree进行排序 两个值进行排序就可以了 1个值判断是否到此结束
    private Set<GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return status.compareTo(AccountStatus.ENABLE.getCode()) == 0;
    }
}
