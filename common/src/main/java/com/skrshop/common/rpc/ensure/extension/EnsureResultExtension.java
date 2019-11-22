package com.skrshop.common.rpc.ensure.extension;

import com.skrshop.common.error.SkrShopException;
import com.skrshop.common.response.BaseResponse;
import com.skrshop.common.error.CommonResultCode;
import com.skrshop.common.error.ResultCode;
import com.skrshop.common.rpc.ensure.Ensure;
import org.apache.commons.collections.CollectionUtils;

import java.util.Objects;

/**
 * @author penglu
 * @version 1.0.0
 * @date 2019-09-12
 * @copyright 本内容仅限于浙江云贸科技有限公司内部传阅，禁止外泄以及用于其他的商业目的
 */
public class EnsureResultExtension {

    private BaseResponse param;

    public EnsureResultExtension(BaseResponse param) {
        this.param = param;
    }

    public EnsureResultExtension isNotNull(ResultCode errorCode) {
        if (this.param == null) {
            throw new SkrShopException(errorCode);
        }
        return this;
    }

    public EnsureResultExtension isNotNullData(ResultCode errorCode) {
        Ensure.that(param).isNotNull(CommonResultCode.REMOTE_REQUEST_ERROR);
        Ensure.that(param.isSuccess()).isTrue(CommonResultCode.REMOTE_REQUEST_ERROR);
        if (Objects.isNull(this.param.getData())) {
            throw new SkrShopException(errorCode);
        }
        return this;
    }

    public EnsureResultExtension isNotEmptyData(ResultCode errorCode) {
        Ensure.that(param).isNotNull(CommonResultCode.FAILURE);
        Ensure.that(param.isSuccess()).isTrue(CommonResultCode.REMOTE_REQUEST_ERROR);
        if (CollectionUtils.sizeIsEmpty(this.param.getData())) {
            throw new SkrShopException(errorCode);
        }
        return this;
    }

    /**
     * @param errorCode
     * @return
     */
    public EnsureResultExtension isSuccess(ResultCode errorCode) {
        Ensure.that(param).isNotNull(CommonResultCode.REMOTE_REQUEST_ERROR);
        if (param.isSuccess()) {
            return this;
        }
        throw new SkrShopException(errorCode);
    }


}
