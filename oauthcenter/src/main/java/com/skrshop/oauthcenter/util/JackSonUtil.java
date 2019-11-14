package com.skrshop.oauthcenter.util;


import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JackSonUtil {
    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(JackSonUtil.class);

    private final static ObjectMapper objectMapper = new ObjectMapper()
            .configure(JsonParser.Feature.ALLOW_COMMENTS, true)
            .configure(JsonParser.Feature.ALLOW_COMMENTS, true)
            .configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)
            .configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true)
            .configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true)
            .configure(JsonParser.Feature.INTERN_FIELD_NAMES, true)
            .configure(JsonParser.Feature.CANONICALIZE_FIELD_NAMES, true)
            .configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);


    public static String encode(Object obj) {
        //$NON-NLS-1$
        //$NON-NLS-1$
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            logger.error("encode(Object)", e);
        }
        return null;
    }

    /**
     * 将json string反序列化成对象
     *
     * @param json
     * @param valueType
     * @return
     */
    public static <T> T decode(String json, Class<T> valueType) {
        try {
            return objectMapper.readValue(json, valueType);
        } catch (IOException e) {
            logger.error("decode(String, Class<T>)", e);
        }
        return null;
    }

    /**
     * 将json array反序列化为对象
     *
     * @param json
     * @param jsonTypeReference
     * @return
     */
    public static <T> T decode(String json, TypeReference<T> jsonTypeReference) {
        try {
            return (T) objectMapper.readValue(json, jsonTypeReference);
        } catch (IOException e) {
            logger.error("decode(String, JsonTypeReference<T>)", e);
        }
        return null;
    }
}
