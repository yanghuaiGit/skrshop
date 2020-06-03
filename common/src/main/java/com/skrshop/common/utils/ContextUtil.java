package com.skrshop.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 获取spring上下文
 */
public class ContextUtil implements ApplicationContextAware {

    public static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    // 这里使用的是根据class类型来获取bean 当然你可以根据名称或者其他之类的方法 主要是有applicationContext你想怎么弄都可以
    public static Object getBeanByClass(Class clazz) {
        return applicationContext.getBean(clazz);
    }
}
