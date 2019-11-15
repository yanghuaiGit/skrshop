package com.skrshop.common.rpc.ensure.extension;


import com.skrshop.common.error.ServiceException;
import com.skrshop.common.error.ResultCode;

public class EnsureEnumExtension {

    private Enum param;

    public EnsureEnumExtension(Enum param) {
        this.param = param;
    }

    public EnsureEnumExtension isEqual(Enum param, ResultCode errorCode) {
        if (this.param != param) {
            throw new ServiceException(errorCode);
        }
        return this;
    }

    public EnsureEnumExtension isNotEqual(Enum param, ResultCode errorCode) {
        if (this.param == param) {
            throw new ServiceException(errorCode);
        }
        return this;
    }

    public EnsureEnumExtension isNotNull(ResultCode errorCode) {
        if (this.param == null) {
            throw new ServiceException(errorCode);
        }
        return this;
    }

}
