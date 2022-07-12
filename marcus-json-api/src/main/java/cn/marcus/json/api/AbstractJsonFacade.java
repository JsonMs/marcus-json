package cn.marcus.json.api;

import cn.marcus.json.api.exception.JsonException;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Predicate;
import com.jayway.jsonpath.TypeRef;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.FileOutputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 抽象json接口实现
 * @author 北城微雨
 * @date 2022/5/25
 */
@Slf4j
public abstract class AbstractJsonFacade implements JsonFacade{

    @Override
    public <T> T parseObject(Object object, Class<T> clazz) {
        return parseObject(toJSONString(object), clazz);
    }

    @Override
    public <T> T parseObject(Object object, TypeReference<T> valueTypeRef) {
        return parseObject(toJSONString(object), valueTypeRef);
    }

    /**
     * 读取文件为字符串
     * @param path 读取路径
     * @return json字符串
     */
    protected String readFile2String(String path){
        try {
            return IOUtils.toString(JsonUtil.class.getClass().getClassLoader().getResourceAsStream(path), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw JsonException.newEx(String.format("[JsonUtil] 路径：%s无法寻找到文件 ==> ", path).concat(e.getMessage()));
        }
    }

    @Override
    public <T> T readJson(String path,Class<T> clazz){
        return parseObject(readFile2String(path),clazz);
    }

    @Override
    public <T> T readJson(String path,TypeReference<T> valueTypeRef){
        return parseObject(readFile2String(path),valueTypeRef);
    }

    @Override
    public <T> List<T> readJsonArray(String path, Class<T> clazz){
        return parseArray(readFile2String(path),clazz);
    }

    @Override
    @SneakyThrows
    public void writeJson(String path,Object object){
        writeJson(new FileOutputStream(path),object);
    }

    @Override
    public Object read(Object json, String jsonPath,Predicate... predicates){
        return JsonPath.read(json, jsonPath,predicates);
    }

    @Override
    public <T> T read(Object json, String jsonPath,Class<T> clazz,Predicate... predicates){
        return JsonPath.parse(json).read(jsonPath,clazz,predicates);
    }

    @Override
    public <T> T read(Object json, String jsonPath,TypeReference<T> valueTypeRef){
        return JsonPath.parse(json).read(jsonPath, new TypeRef<T>() {
            @Override
            public Type getType() {
                return valueTypeRef.getType();
            }
        });
    }

    @Override
    public void set(Object jsonObject,String path,Object object){
        JsonPath.parse(jsonObject).set(path, object);
    }

}
