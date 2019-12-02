package com.skrshop.securitycore.properties;

import lombok.Data;

@Data
public class ValidateCodeProperties {

    private ImageCodeProperties image = new ImageCodeProperties();

    private CodeProperties sms = new CodeProperties();
}
