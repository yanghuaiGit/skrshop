package com.skrshop.oauthcenter.endpoint;

import com.skrshop.oauthcenter.model.vo.UrlInfoVo;
import com.skrshop.oauthcenter.model.vo.UrlVo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/url")
public class UrslEndpoint {

    @Value("${spring.application.name}")
    private String name;

    @GetMapping()
    public UrlVo getAllUrl(HttpServletRequest httpServletRequest) {
        WebApplicationContext requiredWebApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(httpServletRequest.getServletContext());
        RequestMappingHandlerMapping mapping = requiredWebApplicationContext.getBean(RequestMappingHandlerMapping.class);
        //获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        List<UrlInfoVo> urlList = new ArrayList<>();
        for (RequestMappingInfo info : map.keySet()) {
            //获取url的Set集合，一个方法可能对应多个url
            List<String> patterns = new ArrayList<>(info.getPatternsCondition().getPatterns());
            List<String> collect = info.getMethodsCondition().getMethods().stream().map(item -> item.name()).collect(Collectors.toList());
            for (int i = 0; i < patterns.size(); i++) {
                //Restful 形式接口进行替换
                String url = patterns.get(i).replaceAll("\\{([^}]*)\\}", "*");
                if (collect.size() == 0) {
                    Arrays.stream(RequestMethod.values()).forEach(item -> urlList.add(new UrlInfoVo(url, item.name())));
                } else {
                    urlList.add(new UrlInfoVo(url, Optional.ofNullable(collect.get(i)).orElse("")));
                }
            }
        }
        return new UrlVo(urlList, Objects.requireNonNull(requiredWebApplicationContext.getParent()).getId(), Objects.requireNonNull(requiredWebApplicationContext.getServletContext()).getContextPath());
    }
}
