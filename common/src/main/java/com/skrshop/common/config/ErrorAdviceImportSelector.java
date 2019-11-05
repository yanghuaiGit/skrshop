package com.skrshop.common.config;

import com.skrshop.common.error.GlobalExceptionTranslator;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author huaiyang
 * @version 1.0.0
 * @date 2019/10/16
 * @description
 */
public class ErrorAdviceImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{GlobalExceptionTranslator.class.getName()};
    }
}
