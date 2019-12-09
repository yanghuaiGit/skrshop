package com.skrshop.oauthcenter.security.userdetail;

import lombok.Data;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class UserDetailsRepositoryManager implements UserDetailsManager {

    private PasswordEncoder passwordEncoder;

    @Resource
    private List<UserServiceSupport> delagate;

    private static Map<String, UserDetails> users = new HashMap<>();

    public UserDetailsRepositoryManager(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

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

        return delagate
                .stream()
                .filter(item -> item.support(username))
                .map(item -> item.loadUserByUsername(username))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException(username));
//        AuthorityUtils.commaSeparatedStringToAuthorityList("");将字符串转为对应的权限
        //  return User.withUsername("Felordcn").password(passwordEncoder.encode("12345")).authorities(AuthorityUtils.NO_AUTHORITIES).build();
    }

}
