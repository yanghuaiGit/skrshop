package com.skrshop.common.rpc;

import com.skrshop.common.response.BaseResponse;
import com.skrshop.common.error.CommonResultCode;
import com.skrshop.common.error.ResultCode;
import com.skrshop.common.rpc.ensure.Ensure;

public class RpcData {


    public static <T> T of(BaseResponse<T> BaseResponse) {
        Ensure.that(BaseResponse).isNotNullData(CommonResultCode.REMOTE_DATA_NULL_ERROR);
        return BaseResponse.getData();
    }

    /**
     * 校验远程调用结果,如果data属性为空,则抛出给定异常
     * 如果无异常,则返回data
     *
     * @param BaseResponse 远程调用结果
     * @return
     */
    public static <T> T of(BaseResponse<T> BaseResponse, ResultCode BaseResponseStatus) {
        Ensure.that(BaseResponse).isNotNullData(BaseResponseStatus);
        return BaseResponse.getData();
    }

    /**
     * 校验远程调用结果,如果d调用结果失败,则抛出远程服务调用异常
     * 如果无异常,则返回data
     *
     * @param BaseResponse 远程调用结果
     * @return
     */
    public static <T> T ofNullable(BaseResponse<T> BaseResponse) {
        Ensure.that(BaseResponse).isSuccess(CommonResultCode.REMOTE_REQUEST_ERROR);
        return BaseResponse.getData();
    }


}
