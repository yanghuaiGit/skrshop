package com.skrshop.earthsystemapi.api;

import com.skrshop.common.response.BaseResponse;
import com.skrshop.earthsystemapi.fallback.AccountUserApiFallBack;
import com.skrshop.earthsystemapi.model.dto.AccountUserDto;
import com.skrshop.earthsystemapi.model.vo.AccountUserVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author huaiyang
 * @version 1.0.0
 * @date 2019/11/6
 * @description
 * @copyright 本内容仅限于深圳市天行云供应链有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */

@FeignClient(name = "skrshop-earth-system", path = "skrshop/earthsystem", fallback = AccountUserApiFallBack.class)
public interface AccountUserApi {

    @PostMapping("/accountUserManagement/create")
    BaseResponse<AccountUserVo> createAccountUser(@RequestBody AccountUserDto accountUserDto);

    @GetMapping("/accountUserManagement/{id}")
    BaseResponse<AccountUserVo> queryById(@PathVariable("id") Long id);
}
