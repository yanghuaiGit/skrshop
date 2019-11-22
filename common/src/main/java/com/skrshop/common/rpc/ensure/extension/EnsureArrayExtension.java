package com.skrshop.common.rpc.ensure.extension;

import com.skrshop.common.error.SkrShopException;
import com.skrshop.common.error.CommonResultCode;
import com.skrshop.common.error.ResultCode;

import java.util.Collection;
import java.util.Objects;


public class EnsureArrayExtension {
    private Object[] param;

    public EnsureArrayExtension(Object[] param) {
        this.param = param;
    }

    public EnsureArrayExtension isNotEmpty(CommonResultCode errorCode) {
        if (Objects.nonNull(this.param) && this.param.length > 0) {
            return this;
        }
        throw new SkrShopException(errorCode);
    }

    public EnsureArrayExtension isNotNull(CommonResultCode errorCode) {
        if (this.param == null) {
            throw new SkrShopException(errorCode);
        }
        return this;
    }

    public EnsureArrayExtension isEqualTo(Collection param, CommonResultCode errorCode) {
        if (!Objects.equals(this.param, param)) {
            throw new SkrShopException(errorCode);
        }
        return this;
    }

    public EnsureArrayExtension isNotEqualTo(Collection param, ResultCode errorCode) {
        if (Objects.equals(this.param, param)) {
            throw new SkrShopException(errorCode);
        }
        return this;
    }
}
