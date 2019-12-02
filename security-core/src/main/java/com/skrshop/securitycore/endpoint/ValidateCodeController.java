package com.skrshop.securitycore.endpoint;

import com.skrshop.common.error.SkrShopException;
import com.skrshop.securitycore.model.SecurityCoreResultCode;
import com.skrshop.securitycore.validate.ValidateCodeProcessor;
import com.skrshop.securitycore.validate.code.ValidateCodeType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 获取各种验证码的端点
 */
@RestController
@RequestMapping("/validate")
public class ValidateCodeController {

    @Resource
    private Map<String, ValidateCodeProcessor> validateCodeProcessors;

    /**
     * 请求验证码
     */
    @GetMapping("/{createType}")
    public void createValidateCode(HttpServletRequest request,
                                   HttpServletResponse response,
                                   @PathVariable(name = "createType") String createType) throws Exception {
        String type = ValidateCodeType
                .findType(createType)
                .orElseThrow(() -> new SkrShopException(SecurityCoreResultCode.CODE_TYPE_NOT_SUPPORT));

        validateCodeProcessors.get(type + "ValidateCodeProcessor")
                .create(new ServletWebRequest(request, response));
    }
}
