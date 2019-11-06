package com.skrshop.earthsystem.service;

import com.skrshop.earthsystemapi.model.dto.AccountUserDto;
import com.skrshop.earthsystemapi.model.vo.AccountUserVo;

/**
 * @author huaiyang
 * @version 1.0.0
 * @date 2019/11/6
 * @description
 * @copyright 本内容仅限于深圳市天行云供应链有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public interface AccountUserService {

    AccountUserVo create(AccountUserDto accountUserDto);
}
