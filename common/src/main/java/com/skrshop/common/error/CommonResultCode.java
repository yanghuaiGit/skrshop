package com.skrshop.common.error;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletResponse;

@AllArgsConstructor
@NoArgsConstructor
public class CommonResultCode implements ResultCode {

    private int code;

    private String desc;

    private String msg;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    public CommonResultCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    static ResultCode resultCodeFactory(ResultCode resultCode, String msg) {
        return new CommonResultCode(resultCode.getCode(), resultCode.getDesc(), msg);
    }

    public static ResultCode SUCCESS = new CommonResultCode(HttpServletResponse.SC_OK, "Operation is Successful");

    public static ResultCode FAILURE = new CommonResultCode(HttpServletResponse.SC_BAD_REQUEST, "Biz Exception");

    public static ResultCode UN_AUTHORIZED = new CommonResultCode(HttpServletResponse.SC_UNAUTHORIZED, "Request Unauthorized");

    public static ResultCode NOT_FOUND = new CommonResultCode(HttpServletResponse.SC_NOT_FOUND, "404 Not Found");

    public static ResultCode MSG_NOT_READABLE = new CommonResultCode(HttpServletResponse.SC_BAD_REQUEST, "Message Can't be Read");

    public static ResultCode METHOD_NOT_SUPPORTED = new CommonResultCode(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Method Not Supported");

    public static ResultCode MEDIA_TYPE_NOT_SUPPORTED = new CommonResultCode(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "Media Type Not Supported");

    public static ResultCode REQ_REJECT = new CommonResultCode(HttpServletResponse.SC_FORBIDDEN, "Request Rejected");

    public static ResultCode INTERNAL_SERVER_ERROR = new CommonResultCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error");

    public static ResultCode PARAM_MISS = new CommonResultCode(HttpServletResponse.SC_BAD_REQUEST, "Missing Required Parameter");

    public static ResultCode PARAM_TYPE_ERROR = new CommonResultCode(HttpServletResponse.SC_BAD_REQUEST, "Parameter Type Mismatch");

    public static ResultCode PARAM_BIND_ERROR = new CommonResultCode(HttpServletResponse.SC_BAD_REQUEST, "Parameter Binding Error");

    public static ResultCode PARAM_VALID_ERROR = new CommonResultCode(HttpServletResponse.SC_BAD_REQUEST, "Parameter Validation Error");


    //==================数据库异常
    //插入失败异常
    public static ResultCode INSERT_DB_ERROR = new CommonResultCode(1001, "INSERT_DB_EXCEPTION");

    //RPC远程服务异常
    public static ResultCode REMOTE_REQUEST_ERROR = new CommonResultCode(2000, "REMOTE SERVICE EXCEPTION");

    public static ResultCode REMOTE_DATA_NULL_ERROR = new CommonResultCode(2000, "REMOTE SERVICE EXCEPTION");


    //=============================业务异常

    //权限异常 4000 - 4500
    public static ResultCode AUTH_ERROR = new CommonResultCode(4000, "Auth Error");

}
