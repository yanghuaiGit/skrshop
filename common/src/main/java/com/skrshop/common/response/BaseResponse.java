package com.skrshop.common.response;


import java.util.Map;

public class BaseResponse<T> {


    private Integer code;

    private String desc;

    private String message;

    private T data;

    private Map<String, Object> extra;

    private BaseResponse(Integer code, String desc, String message, T data, Map<String, Object> extra) {
        this.code = code;
        this.desc = desc;
        this.message = message;
        this.data = data;
        this.extra = extra;
    }

    public static BaseResponseBuilder code() {
        return code(ResultCode.SUCCESS);
    }

    public static BaseResponseBuilder code(ResultCode resultCode) {
        return new BaseResponseBuilder(resultCode);
    }

    public static class BaseResponseBuilder<T> {

        private ResultCode resultCode;

        private String message;

        private T data;

        private Map<String, Object> extra;

        public BaseResponseBuilder(ResultCode resultCode) {
            this.resultCode = resultCode;
        }

        public BaseResponseBuilder data(T data) {
            this.data = data;
            return this;
        }

        public BaseResponseBuilder message(String message) {
            this.message = message;
            return this;
        }

        public BaseResponseBuilder extra(Map<String, Object> extra) {
            this.extra = extra;
            return this;
        }

        public BaseResponse<T> build() {
            return new BaseResponse(resultCode.getCode(), resultCode.getMsg(), message, data, extra);
        }
    }

}
