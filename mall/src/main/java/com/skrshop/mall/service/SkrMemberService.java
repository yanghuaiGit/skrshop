package com.skrshop.mall.service;

import com.skrshop.mall.model.dto.SkrMemberDto;
import com.skrshop.mall.model.vo.SkrMemberVo;
import org.springframework.web.bind.annotation.RequestBody;

public interface SkrMemberService {
    SkrMemberVo create(SkrMemberDto skrMemberDto);
}
