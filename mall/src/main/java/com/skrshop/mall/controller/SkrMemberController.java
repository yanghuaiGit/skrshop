package com.skrshop.mall.controller;


import com.skrshop.common.response.BaseResponse;
import com.skrshop.mall.model.dto.SkrMemberDto;
import com.skrshop.mall.service.SkrMemberService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class SkrMemberController {

    @Resource
    private SkrMemberService skrMemberService;

    @PostMapping("/create")
    public BaseResponse create(@RequestBody SkrMemberDto skrMemberDto) {
        return BaseResponse.code().data(skrMemberService.create(skrMemberDto)).build();
    }
}
