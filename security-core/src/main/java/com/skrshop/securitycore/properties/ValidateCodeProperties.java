package com.skrshop.securitycore.properties;

import lombok.Data;

@Data
public class ValidateCodeProperties {

    private CodeProperties sms = new CodeProperties();
}
