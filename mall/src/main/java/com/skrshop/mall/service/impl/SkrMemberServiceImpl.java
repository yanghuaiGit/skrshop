package com.skrshop.mall.service.impl;

import com.skrshop.common.config.DozerHolder;
import com.skrshop.common.response.BaseResponse;
import com.skrshop.common.error.ResultCode;
import com.skrshop.common.rpc.RpcData;
import com.skrshop.earthsystemapi.api.AccountUserApi;
import com.skrshop.earthsystemapi.model.dto.AccountUserDto;
import com.skrshop.earthsystemapi.model.vo.AccountUserVo;
import com.skrshop.mall.model.dto.SkrMemberDto;
import com.skrshop.mall.model.po.SkrMember;
import com.skrshop.mall.model.vo.SkrMemberVo;
import com.skrshop.mall.repo.skrmember.SkrMemberMapper;
import com.skrshop.mall.service.SkrMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class SkrMemberServiceImpl implements SkrMemberService {

    @Resource
    private DozerHolder dozerHolder;

    @Resource
    private SkrMemberMapper skrMemberMapper;

    @Resource
    private AccountUserApi accountUserApi;

    @Override
    public SkrMemberVo create(SkrMemberDto skrMemberDto) {
        AccountUserDto accountUserDto = new AccountUserDto();
        accountUserDto.setType(1);
        accountUserDto.setOrign(0);
        accountUserDto.setPassword(skrMemberDto.getPassword());
        accountUserDto.setPhone(skrMemberDto.getPhone());
        accountUserDto.setEmail(skrMemberDto.getEmail());
        accountUserDto.setUsername(skrMemberDto.getUsername());

        BaseResponse<AccountUserVo> accountUser = accountUserApi.createAccountUser(accountUserDto);

        SkrMember skrMember = dozerHolder.convert(skrMemberDto, SkrMember.class);
        skrMember.setUid(RpcData.of(accountUser, ResultCode.REMOTE_DATA_NULL_ERROR).getId());

        return dozerHolder.convert(skrMemberMapper.createAndReturn(skrMember), SkrMemberVo.class);
    }
}
