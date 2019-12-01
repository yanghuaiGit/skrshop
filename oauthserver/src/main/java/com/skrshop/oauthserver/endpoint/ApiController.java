package com.skrshop.oauthserver.endpoint;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

    @RequestMapping("/user")
    public UserInfo getUserInfo() {
        return new UserInfo("yh", 18);
    }

    @Data
    @AllArgsConstructor
    static class UserInfo {
        private String name;
        private int age;
    }
}
