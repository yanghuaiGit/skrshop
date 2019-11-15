package com.skrshop.oauthcenter.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlVo implements Serializable {
    private List<UrlInfoVo> urls;
    private String id;
    private String path;


}
