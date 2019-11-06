package com.skrshop.common.config;

import org.dozer.Mapper;
import org.dozer.util.MappingValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * dozer工具类
 *
 * @author yh
 * @version 1.0.0
 * @date 2019-11-04
 */

@Configuration
public class DozerHolder {

    private Mapper dozerMapper;

    public DozerHolder(Mapper dozerMapper) {
        this.dozerMapper = dozerMapper;
    }

    /**
     * 集合转换
     *
     * @param source
     * @param destinationClass
     * @param <T>
     * @return
     */
    public <T> List<T> convert(List source, Class<T> destinationClass) {
        if (CollectionUtils.isEmpty(source)) {
            return new ArrayList<>();
        }
        MappingValidator.validateMappingRequest(source, destinationClass);
        List target = new ArrayList(source.size());
        for (Object each : source) {
            target.add(dozerMapper.map(each, destinationClass));
        }
        return target;
    }

    /**
     * @param
     * @return
     * @Description :转换单个对象
     * @author :yh
     * @Date :2019-08-19 23:37
     */

    public <T> T convert(Object source, Class<T> destinationClass) {
        MappingValidator.validateMappingRequest(source, destinationClass);
        return dozerMapper.map(source, destinationClass);
    }
}
