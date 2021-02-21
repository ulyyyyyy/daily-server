package com.heihei.daily.utils;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

/**
 * json工具类
 *
 * Author: heihei
 */
public class JsonUtils {

    public static String object2Json(Object obj) {
        String result = null;

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            result = objectMapper.writeValueAsString(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Map object2Map(Object obj) {
        String object2Json = object2Json(obj);
        Map<?, ?> result = jsonToMap(object2Json);
        return result;
    }

    public static Map<?, ?> jsonToMap(String json) {
        return json2Object(json, Map.class);
    }

    private static <T> T json2Object(String json, Class<T> mapClass) {
        T result = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            result = objectMapper.readValue(json, mapClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static <T> T conveterObject(Object srcObject, Class<T> destObjectType) {
        String jsonContent = object2Json(srcObject);
        return json2Object(jsonContent, destObjectType);
    }

}
