package com.skrshop.earthsystem.controller;

import com.skrshop.common.response.BaseResponse;
import com.skrshop.earthsystem.service.AccountUserService;
import com.skrshop.earthsystemapi.api.AccountUserApi;
import com.skrshop.earthsystemapi.model.dto.AccountUserDto;
import com.skrshop.earthsystemapi.model.vo.AccountUserVo;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author huaiyang
 * @version 1.0.0
 * @date 2019/11/5
 * @description 账户入口 对外只提供uid的产生
 */
@Api(tags = "账户")
@RestController
@RequestMapping(value = "/accountUserManagement")
public class AccountUserController implements AccountUserApi {

    @Resource
    private AccountUserService accountUserService;

    @Override
    @PostMapping(path = "/create")
    public BaseResponse<AccountUserVo> createAccountUser(@RequestBody AccountUserDto accountUserDto) {
        return BaseResponse.code().data(accountUserService.create(accountUserDto)).build();
    }
}
