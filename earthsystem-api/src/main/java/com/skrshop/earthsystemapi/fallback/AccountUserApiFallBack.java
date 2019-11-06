package com.skrshop.earthsystemapi.fallback;

import com.skrshop.common.response.BaseResponse;
import com.skrshop.common.response.ResultCode;
import com.skrshop.earthsystemapi.api.AccountUserApi;
import com.skrshop.earthsystemapi.model.dto.AccountUserDto;
import com.skrshop.earthsystemapi.model.vo.AccountUserVo;
import org.springframework.stereotype.Component;

/**
 * @author huaiyang
 * @version 1.0.0
 * @date 2019/11/6
 * @description
 */

//@Component
public class AccountUserApiFallBack implements AccountUserApi {
    @Override
    public BaseResponse<AccountUserVo> createAccountUser(AccountUserDto accountUserDto) {
        return BaseResponse.code(ResultCode.FAILURE).message("Feign失败").build();
    }
}