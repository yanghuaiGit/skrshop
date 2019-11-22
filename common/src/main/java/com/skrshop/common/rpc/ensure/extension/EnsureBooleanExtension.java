package com.skrshop.common.rpc.ensure.extension;

import com.skrshop.common.error.ResultCode;
import com.skrshop.common.error.SkrShopException;
import org.apache.commons.lang3.BooleanUtils;


public class EnsureBooleanExtension {

    private Boolean param;

    public EnsureBooleanExtension(Boolean param) {
        this.param = param;
    }

    public EnsureBooleanExtension isFalse(ResultCode errorCode) {
        if (BooleanUtils.toBoolean(this.param)) {
            throw new SkrShopException(errorCode);
        }
        return this;
    }

    public EnsureBooleanExtension isTrue(ResultCode errorCode) {
        if (!BooleanUtils.toBoolean(this.param)) {
            throw new SkrShopException(errorCode);
        }
        return this;
    }

    public EnsureBooleanExtension isNotNull(ResultCode errorCode) {
        if (this.param == null) {
            throw new SkrShopException(errorCode);
        }
        return this;
    }
}
