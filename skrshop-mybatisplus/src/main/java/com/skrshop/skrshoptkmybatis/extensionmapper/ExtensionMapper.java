package com.skrshop.skrshoptkmybatis.extensionmapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.skrshop.common.error.SkrShopException;
import com.skrshop.common.error.CommonResultCode;
import com.skrshop.common.error.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface ExtensionMapper<T> extends BaseMapper {
    Logger LOGGER = LoggerFactory.getLogger(ExtensionMapper.class);

    default void createSuccess(T data, ResultCode resultCode) {
        int result = this.insert(data);
        if (result != 1) {
            LOGGER.error("添加 {} 失败，影响行数result {}", data, result);
            throw new SkrShopException(resultCode);
        }
    }

    default void createSuccess(T data) {
        createSuccess(data, CommonResultCode.INSERT_DB_ERROR);
    }

    default T createAndReturn(T data) {
        createSuccess(data);
        return data;
    }
}
