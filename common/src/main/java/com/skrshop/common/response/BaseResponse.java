package com.skrshop.common.response;


import com.skrshop.common.error.CommonResultCode;
import com.skrshop.common.error.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BaseResponse<T> {


    private Integer code;

    private String desc;

    private String message;

    private T data;

    private Map<String, Object> extra;


    public Boolean isSuccess() {
        return CommonResultCode.SUCCESS.getCode().equals(code);
    }


    public static BaseResponseBuilder code() {
        return code(CommonResultCode.SUCCESS);
    }

    public static BaseResponseBuilder code(ResultCode resultCode) {
        return new BaseResponseBuilder(resultCode);
    }

    public static class BaseResponseBuilder<T> {

        private ResultCode resultCode;

        private T data;

        private Map<String, Object> extra;

        public BaseResponseBuilder(ResultCode resultCode) {
            this.resultCode = resultCode;
        }

        public BaseResponseBuilder data(T data) {
            this.data = data;
            return this;
        }


        public BaseResponseBuilder extra(Map<String, Object> extra) {
            this.extra = extra;
            return this;
        }

        public BaseResponse<T> build() {
            return new BaseResponse(resultCode.getCode(), resultCode.getDesc(), resultCode.getMsg(), data, extra);
        }
    }

}
