package com.skrshop.common.rpc.ensure.extension;

import com.skrshop.common.error.ServiceException;
import com.skrshop.common.response.ResultCode;

import java.util.Collection;
import java.util.Objects;


public class EnsureArrayExtension {
    private Object[] param;

    public EnsureArrayExtension(Object[] param) {
        this.param = param;
    }

    public EnsureArrayExtension isNotEmpty(ResultCode errorCode) {
        if (Objects.nonNull(this.param) && this.param.length > 0) {
            return this;
        }
        throw new ServiceException(errorCode);
    }

    public EnsureArrayExtension isNotNull(ResultCode errorCode) {
        if (this.param == null) {
            throw new ServiceException(errorCode);
        }
        return this;
    }

    public EnsureArrayExtension isEqualTo(Collection param, ResultCode errorCode) {
        if (!Objects.equals(this.param, param)) {
            throw new ServiceException(errorCode);
        }
        return this;
    }

    public EnsureArrayExtension isNotEqualTo(Collection param, ResultCode errorCode) {
        if (Objects.equals(this.param, param)) {
            throw new ServiceException(errorCode);
        }
        return this;
    }
}
