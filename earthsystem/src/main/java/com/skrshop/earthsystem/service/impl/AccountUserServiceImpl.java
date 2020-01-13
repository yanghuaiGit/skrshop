package com.skrshop.earthsystem.service.impl;

import com.skrshop.common.config.DozerHolder;
import com.skrshop.earthsystem.common.enums.AccountTypeEnum;
import com.skrshop.earthsystem.model.po.account.AccountUser;
import com.skrshop.earthsystem.repo.account.AccountUserMapper;
import com.skrshop.earthsystem.service.AccountUserService;
import com.skrshop.earthsystemapi.model.dto.AccountUserDto;
import com.skrshop.earthsystemapi.model.vo.AccountUserVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author huaiyang
 * @version 1.0.0
 * @date 2019/11/6
 * @description
 */

@Service
public class AccountUserServiceImpl implements AccountUserService {

    @Resource
    private AccountUserMapper accountUserMapper;

    @Resource
    private DozerHolder dozerHolder;

    @Override
    public AccountUserVo create(AccountUserDto accountUserDto) {
        AccountUser accountUser = dozerHolder.convert(accountUserDto, AccountUser.class);
        return dozerHolder.convert(accountUserMapper.createAndReturn(accountUser), AccountUserVo.class);
    }

    @Override
    public AccountUserVo queryById(Long id) {
        AccountUser accountUser = (AccountUser) accountUserMapper.selectById(id);
        return dozerHolder.convert(accountUser,AccountUserVo.class);
    }
}
