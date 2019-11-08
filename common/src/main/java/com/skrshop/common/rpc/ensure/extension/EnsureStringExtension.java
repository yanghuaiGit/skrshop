package com.skrshop.common.rpc.ensure.extension;

import com.skrshop.common.error.ServiceException;
import com.skrshop.common.response.ResultCode;
import org.apache.commons.lang3.StringUtils;


public class EnsureStringExtension {

    private String param;

    public EnsureStringExtension(String param) {
        this.param = param;
    }

    public EnsureStringExtension isNotNull(ResultCode errorCode) {
        if (this.param == null) {
            throw new ServiceException(errorCode);
        }
        return this;
    }

    public EnsureStringExtension isNotEmpty(ResultCode errorCode) {
        if (StringUtils.isEmpty(this.param)) {
            throw new ServiceException(errorCode);
        }
        return this;
    }

    public EnsureStringExtension isNotBlank(ResultCode errorCode) {
        if (StringUtils.isBlank(this.param)) {
            throw new ServiceException(errorCode);
        }
        return this;
    }

    public EnsureStringExtension isEqualTo(String param, ResultCode errorCode) {
        if (!StringUtils.equals(this.param, param)) {
            throw new ServiceException(errorCode);
        }
        return this;
    }

    public EnsureStringExtension isNotEqualTo(String param, ResultCode errorCode) {
        if (StringUtils.equals(this.param, param)) {
            throw new ServiceException(errorCode);
        }
        return this;
    }

}
