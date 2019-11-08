package com.skrshop.common.rpc.ensure;

import com.skrshop.common.response.BaseResponse;
import com.skrshop.common.rpc.ensure.extension.*;

import java.util.Collection;

public interface Ensure {
    public static EnsureObjectExtension that(Object param) {
        return new EnsureObjectExtension(param);
    }

    public static EnsureArrayExtension that(Object[] param) {
        return new EnsureArrayExtension(param);
    }

    public static EnsureCollectionExtension that(Collection param) {
        return new EnsureCollectionExtension(param);
    }

    public static EnsureBooleanExtension that(Boolean param) {
        return new EnsureBooleanExtension(param);
    }

    public static EnsureNumberExtension that(Number param) {
        return new EnsureNumberExtension(param);
    }

    public static EnsureEnumExtension that(Enum param) {
        return new EnsureEnumExtension(param);
    }

    public static EnsureStringExtension that(String param) {
        return new EnsureStringExtension(param);
    }

    public static EnsureResultExtension that(BaseResponse param) {
        return new EnsureResultExtension(param);
    }
}
