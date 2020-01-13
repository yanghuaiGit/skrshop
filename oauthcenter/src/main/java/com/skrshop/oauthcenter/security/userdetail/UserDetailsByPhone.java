package com.skrshop.oauthcenter.security.userdetail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.regex.Pattern;

@Component
@Slf4j
public class UserDetailsByPhone extends UserServiceSupport {
    @Resource
    private PasswordEncoder passwordEncoder;
    private  final Pattern p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");

    @Override
    public boolean support(String userName) {
       // 验证手机号
        return p.matcher(userName).matches();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("手机号码查询");
        return User.withUsername(username).password(passwordEncoder.encode("12345")).authorities(AuthorityUtils.NO_AUTHORITIES).build();
    }
}
