package com.skrshop.oauthcenter.endpoint;


import com.skrshop.oauthcenter.security.validate.code.ValidateCodeProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
public class ValidCodeController {

    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessor;


    @GetMapping("/code/{type}")
    public void createCode(@PathVariable String type) throws Exception {
        validateCodeProcessor.get(type + "CodeProcessor").create();
    }

}
