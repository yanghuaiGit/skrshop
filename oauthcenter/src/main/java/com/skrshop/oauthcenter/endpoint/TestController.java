package com.skrshop.oauthcenter.endpoint;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/test1")
    public String test(){
        return "--";
    }

    @RequestMapping("/test2")
    public String test1(){
        return "-1-";
    }
}
