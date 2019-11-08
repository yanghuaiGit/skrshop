package com.skrshop.common.rpc.ensure.extension;

import com.skrshop.common.error.ServiceException;
import com.skrshop.common.response.ResultCode;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Objects;

public class EnsureCollectionExtension {

    private Collection param;

    public EnsureCollectionExtension(Collection param) {
        this.param = param;
    }

    public EnsureCollectionExtension isNotEmpty(ResultCode errorCode) {
        if (!CollectionUtils.isEmpty(this.param)) {
            return this;
        }
        throw new ServiceException(errorCode);
    }

    public EnsureCollectionExtension isNotNull(ResultCode errorCode) {
        if (this.param == null) {
            throw new ServiceException(errorCode);
        }
        return this;
    }

    public EnsureCollectionExtension isEqualTo(Collection param, ResultCode errorCode) {
        if (!Objects.equals(this.param, param)) {
            throw new ServiceException(errorCode);
        }
        return this;
    }

    public EnsureCollectionExtension isNotEqualTo(Collection param, ResultCode errorCode) {
        if (Objects.equals(this.param, param)) {
            throw new ServiceException(errorCode);
        }
        return this;
    }

}
