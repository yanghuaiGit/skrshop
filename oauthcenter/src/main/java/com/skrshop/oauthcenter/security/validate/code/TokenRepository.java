package com.skrshop.oauthcenter.security.validate.code;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import java.util.Date;

@Data
@AllArgsConstructor
public class TokenRepository implements PersistentTokenRepository {


    private ValueOperations<String, String> valueOperations;

    @Override
    public void createNewToken(PersistentRememberMeToken token) {

    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {

    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        return null;
    }

    @Override
    public void removeUserTokens(String username) {

    }
}
