package com.skrshop.common.log;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


/**
 * 请求日志
 *
 * @param <T>
 */
@Data
@Builder
public class RequestLogPo<T> {

    /**
     * 日志时间
     */
    private LocalDateTime operateTime;

    /**
     * 操作人
     */
    private Long operateId;

    /**
     * 操作描述
     */
    private String operateDesc;

    /**
     * 操作数据
     */
    private T data;
}
