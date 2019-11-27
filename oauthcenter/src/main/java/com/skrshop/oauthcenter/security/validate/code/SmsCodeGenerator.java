package com.skrshop.oauthcenter.security.validate.code;

import cn.hutool.core.util.RandomUtil;
import com.skrshop.oauthcenter.security.config.properties.SkrShopAuthorityCenterProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsCodeGenerator implements ValidateCodeGenerator {

    private SkrShopAuthorityCenterProperties skrShopAuthorityCenterProperties;

    // 验证码范围,去掉0(数字)和O(拼音)容易混淆的(小写的1和L也可以去掉,大写不用了)
    private static char[] codeSequence = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9'};


    @Override
    public ValidateCode generateCode(HttpServletRequest httpServletRequest) {
        return new ValidateCode(RandomUtil.randomNumbers(skrShopAuthorityCenterProperties.getSecurity().getCode().getImage().getLineCount()), 1000L);
    }

}
