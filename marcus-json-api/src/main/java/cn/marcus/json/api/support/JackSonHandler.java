package cn.marcus.json.api.support;

import cn.marcus.json.api.AbstractJsonFacade;
import cn.marcus.json.api.TypeReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.SneakyThrows;

import java.io.OutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * jackSon接口实现
 * @author 北城微雨
 * @date 2022/5/25
 */
public class JackSonHandler extends AbstractJsonFacade {

    /** 转化对象 */
    public static ObjectMapper MAPPER = new ObjectMapper();

    static {
        //允许未知字段
        MAPPER.enable(JsonGenerator.Feature.IGNORE_UNKNOWN);
        //允许序列化空的POJO类，否则序列化空对象是会抛出异常
        MAPPER.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        //序列化不为空
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //强制JSON 空字符串("")转换为null对象值:
        MAPPER.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        //在遇到未知属性的时候不抛出异常。
        MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    @Override
    @SneakyThrows
    public String toJSONString(Object object) {
        return MAPPER.writeValueAsString(object);
    }

    @Override
    @SneakyThrows
    public <T> T parseObject(String jsonStr, Class<T> clazz) {
        JavaType javaType = MAPPER.getTypeFactory().constructType(clazz);
        return MAPPER.readValue(jsonStr, javaType);
    }

    @Override
    @SneakyThrows
    public <T> T parseObject(String jsonStr, TypeReference<T> valueTypeRef) {
        return MAPPER.readValue(jsonStr, new com.fasterxml.jackson.core.type.TypeReference<T>() {
            @Override
            public Type getType() {
                return valueTypeRef.getType();
            }
        });
    }

    @Override
    @SneakyThrows
    public <T> List<T> parseArray(String jsonStr, Class<T> clazz) {
        CollectionType collectionType = MAPPER.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
        return MAPPER.readValue(jsonStr, collectionType);
    }

    @Override
    @SneakyThrows
    public void writeJson(OutputStream os, Object object){
        MAPPER.writeValue(os,object);
    }
}
