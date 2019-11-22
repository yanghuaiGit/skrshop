package com.skrshop.common.rpc.ensure.extension;


import com.skrshop.common.error.SkrShopException;
import com.skrshop.common.error.ResultCode;

import java.util.Objects;

public class EnsureNumberExtension {

    private Number param;

    public EnsureNumberExtension(Number param) {
        this.param = param;
    }

    public EnsureNumberExtension isGt(Number param, ResultCode errorCode) {
        if (this.param.doubleValue() <= param.doubleValue()) {
            throw new SkrShopException(errorCode);
        }
        return this;
    }

    public EnsureNumberExtension isNotGt(Number param, ResultCode errorCode) {
        if (this.param.doubleValue() > param.doubleValue()) {
            throw new SkrShopException(errorCode);
        }
        return this;
    }

    public EnsureNumberExtension isLt(Number param, ResultCode errorCode) {
        if (this.param.doubleValue() >= param.doubleValue()) {
            throw new SkrShopException(errorCode);
        }
        return this;
    }

    public EnsureNumberExtension isNotLt(Number param, ResultCode errorCode) {
        if (this.param.doubleValue() < param.doubleValue()) {
            throw new SkrShopException(errorCode);
        }
        return this;
    }

    public EnsureNumberExtension isEqual(Number param, ResultCode errorCode) {
        if (!Objects.equals(this.param, param)) {
            throw new SkrShopException(errorCode);
        }
        return this;
    }

    public EnsureNumberExtension isNotEqual(Number param, ResultCode errorCode) {
        if (Objects.equals(this.param, param)) {
            throw new SkrShopException(errorCode);
        }
        return this;
    }

    public EnsureNumberExtension isNotNull(ResultCode errorCode) {
        if (this.param == null) {
            throw new SkrShopException(errorCode);
        }
        return this;
    }

    public EnsureNumberExtension isNull(ResultCode errorCode) {
        if (this.param != null) {
            throw new SkrShopException(errorCode);
        }
        return this;
    }

}
