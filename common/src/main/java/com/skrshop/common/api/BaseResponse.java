package com.skrshop.common.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseResponse {
    private String message;
    @Builder.Default
    private ResultCode code = ResultCode.SUCCESS;

    private Map<String, Object> extra;

    public boolean isSuccess() {
        return code == ResultCode.SUCCESS;
    }
}
