package com.ntl.webcore.common.lang.json;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JSONUtil<T> {

    public static<T> void serializeObjectToJsonFile(T object, String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File(filePath), object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static<T> String serializeObjectToJsonStr(T object) {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = null;
        try {
            jsonStr = objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }

    public static<T> T deserializeJSONStrToObject(String jsonStr, Class<T> clazz){
        try {
            T object = new ObjectMapper().readValue( jsonStr, clazz);
            return object;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static<T> T deserializeJSONFileToObject(String fileName, Class<T> clazz){
        try {
            T object = new ObjectMapper().readValue( new File(fileName), clazz);
            return object;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static<T> List<T> deserializeJSONStrToListObject(String jsonStr, Class<T> clazz){
        List<T> list = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            list = new ObjectMapper().readValue(jsonStr, getCollectionType(mapper, List.class, clazz));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static<T> List<T> deserializeJSONFileToListObject(String fileName, Class<T> clazz){
        List<T> list = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            list = new ObjectMapper().readValue( new File(fileName), getCollectionType(mapper, List.class, clazz));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private static JavaType getCollectionType(ObjectMapper mapper, Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
}
