package com.skrshop.oauthcenter.oauth;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
public class CustomClientDetailsService implements ClientDetailsService {

    private PasswordEncoder passwordEncoder;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        BaseClientDetails result = new BaseClientDetails();
        Set<String> set = new HashSet<>();
        set.add("authorization_code");
        set.add("password");
        set.add("refresh_token");
        Set<String> uris = new HashSet<>();
        uris.add("http://www.baidu.com");

        result.setClientId(clientId);
        result.setAuthorizedGrantTypes(set);
        result.setAccessTokenValiditySeconds(3600);
        result.setRefreshTokenValiditySeconds(3600);
        result.setRegisteredRedirectUri(uris);
        result.setClientSecret(passwordEncoder.encode("secret"));
        result.setScope(Collections.singletonList("all"));
//        result.setAuthorities(AuthorityUtils.createAuthorityList(authorities.toArray(new String[authorities.size()])));
//        result.setResourceIds(resourceIds);
//        result.setAdditionalInformation(additionalInformation);
//        if (autoApprove) {
//            result.setAutoApproveScopes(scopes);
//        }
//        else {
//            result.setAutoApproveScopes(autoApproveScopes);
//        }
        return result;


    }
}
