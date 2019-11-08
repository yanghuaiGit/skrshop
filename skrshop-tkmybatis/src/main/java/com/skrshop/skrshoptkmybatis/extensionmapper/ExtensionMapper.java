package com.skrshop.skrshoptkmybatis.extensionmapper;

import com.skrshop.common.error.ServiceException;
import com.skrshop.common.response.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.mybatis.mapper.common.Mapper;

public interface ExtensionMapper<T> extends Mapper {
    Logger LOGGER = LoggerFactory.getLogger(ExtensionMapper.class);

    default void createSuccess(T data, ResultCode resultCode) {
        int result = this.insertSelective(data);
        if (result != 1) {
            LOGGER.error("添加 {} 失败，影响行数result {}", data, result);
            throw new ServiceException(resultCode);
        }
    }

    default void createSuccess(T data) {
        createSuccess(data, ResultCode.INSERT_DB_ERROR);
    }

    default T createAndReturn(T data) {
        createSuccess(data);
        return data;
    }
}
