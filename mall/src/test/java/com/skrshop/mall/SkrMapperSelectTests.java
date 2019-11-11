package com.skrshop.mall;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.skrshop.mall.model.po.SkrMember;
import com.skrshop.mall.repo.skrmember.SkrMemberMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MallApplication.class})
@Slf4j
public class SkrMapperSelectTests {

    @Resource
    private SkrMemberMapper skrMemberMapper;

    @Test
    public void contextLoads() {
    }

    /**
     * 根据主键查询
     */
    @Test
    public void selectById() {

        Long id = 1L;
        log.info("selectByID，主键 {}  ，结果 {}", id, skrMemberMapper.selectById(id));
    }

    /**
     * 根据主键批量查询
     */
    @Test
    public void selectByIds() {
        List<Long> ids = Arrays.asList(1L, 14L);
        log.info("selectByIds，主键集合 {}  ，结果 {}", ids, skrMemberMapper.selectBatchIds(ids));
    }

    /**
     * 根据条件查询
     */
    @Test
    public void selectByMap() {
        Map<String, Object> columnMap = new HashMap<>(4);
        columnMap.put("id", 1);
        log.info("selectByMap，条件 {}  ，结果 {}", columnMap, skrMemberMapper.selectByMap(columnMap));
    }

    /**
     * 根据条件查询
     */
    @Test
    public void selectByWrapper() {
        QueryWrapper<SkrMember> query = Wrappers.query();
        query.eq("id", 1).select("id", "uid");
        log.info("selectByWrapper拼接，条件 {}  ，结果 {}", query, skrMemberMapper.selectList(query));

        SkrMember skrMember = new SkrMember();
        skrMember.setId(2L);
        query = Wrappers.query(skrMember);
        log.info("selectByWrapper根据实体，条件 {}  ，结果 {}", query, skrMemberMapper.selectList(query));

        //防止sql注入
        query.apply("date_format(create_at,'%Y-%m-%d') = {0}", "2019-11-11");
        log.info("selectByWrapper apply，条件 {}  ，结果 {}", query, skrMemberMapper.selectList(query));

        LambdaQueryWrapper<SkrMember> skrMemberLambdaQueryWrapper = Wrappers.<SkrMember>lambdaQuery()
                .select(SkrMember::getNickName)
                .eq(SkrMember::getNickName, "yh");

        log.info("selectByWrapper lambda，条件 {}  ，结果 {}", query, skrMemberMapper.selectList(skrMemberLambdaQueryWrapper));

        List<SkrMember> yh = new LambdaQueryChainWrapper<SkrMember>(skrMemberMapper).select(SkrMember::getNickName)
                .eq(SkrMember::getNickName, "yh").list();

        log.info("selectByWrapper LambdaQueryChainWrapper，条件 {}  ，结果 {}", query, skrMemberMapper.selectList(skrMemberLambdaQueryWrapper));

        Page<User> userPage = new Page<>(1, 1);
        log.info("selectByWrapper page分页，条件 {}  ，结果 {}", userPage, skrMemberMapper.selectPage(userPage, skrMemberLambdaQueryWrapper));


    }
}
