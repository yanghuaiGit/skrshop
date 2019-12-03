package com.skrshop.oauthcenter.security.userdetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class UserDetailsRepository implements UserDetailsManager {

    private PasswordEncoder passwordEncoder;

    private static Map<String, UserDetails> users = new HashMap<>();


    public void createUser(UserDetails user) {
        users.putIfAbsent(user.getUsername(), user);
    }


    public void updateUser(UserDetails user) {
        users.put(user.getUsername(), user);
    }


    public void deleteUser(String username) {
        users.remove(username);
    }


    public void changePassword(String oldPassword, String newPassword) {
        Authentication currentUser = SecurityContextHolder.getContext()
                .getAuthentication();

        if (currentUser == null) {
            // This would indicate bad coding somewhere
            throw new AccessDeniedException(
                    "Can't change password as no Authentication object found in context "
                            + "for current user.");
        }

        String username = currentUser.getName();

        UserDetails user = users.get(username);


        if (user == null) {
            throw new IllegalStateException("Current user doesn't exist in database.");
        }

    }


    public boolean userExists(String username) {

        return users.containsKey(username);
    }


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        AuthorityUtils.commaSeparatedStringToAuthorityList("");将字符串转为对应的权限
//        return users.get(username);
        return User.withUsername("Felordcn").password(passwordEncoder.encode("12345")).authorities(AuthorityUtils.NO_AUTHORITIES).build();
        //  return loginProcessor.getUserDetails(username, RequestHolder.getRequest().getParameter(SPRING_SECURITY_FORM_PASSWORD_KEY));

    }
}
