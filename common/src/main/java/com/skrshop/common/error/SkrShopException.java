package com.skrshop.common.error;

import lombok.Getter;

/**
 * Business Service Exception
 *
 * @author william
 */
public class SkrShopException extends RuntimeException {
    private static final long serialVersionUID = 2359767895161832954L;

    @Getter
    private final ResultCode resultCode;

    public SkrShopException(String message) {
        super(message);
        this.resultCode = CommonResultCode.FAILURE;
    }

    public SkrShopException(ResultCode resultCode) {
        super(resultCode.getMsg());
        this.resultCode = resultCode;
    }

    public SkrShopException(ResultCode resultCode, String msg) {
        super(msg);
        this.resultCode = resultCode;
    }

    public SkrShopException(ResultCode resultCode, Throwable cause) {
        super(cause);
        this.resultCode = resultCode;
    }

    public SkrShopException(String msg, Throwable cause) {
        super(msg, cause);
        this.resultCode = CommonResultCode.FAILURE;
    }

    /**
     * for better performance
     *
     * @return Throwable
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

    public Throwable doFillInStackTrace() {
        return super.fillInStackTrace();
    }
}
