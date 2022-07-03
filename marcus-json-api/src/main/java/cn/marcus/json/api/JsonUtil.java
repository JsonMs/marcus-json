package cn.marcus.json.api;

import cn.marcus.json.base.enums.JsonEnum;
import cn.marcus.json.base.predicate.IfProcess;
import cn.marcus.json.base.predicate.Objects;
import com.jayway.jsonpath.Predicate;

import java.io.OutputStream;
import java.util.List;

/**
 * json基础工具
 * @author 北城微雨
 * @date 2022/5/25
 */
public class JsonUtil {

    /** json接口 */
    private static JsonFacade jsonFacade;

    /**
     * 数据初始化，简化手动初始化操作
     */
    public static void init(){
        init(JsonEnum.JACKSON.toString());
    }

    /**
     * 数据初始化，指定初始化类型
     * @param jsonType
     * @param scanPackage
     */
    public static void init(String jsonType, String... scanPackage) {
        jsonFacade = JsonFactory.init(jsonType, scanPackage);
    }

    /**
     * 转化为json字符串
     * @param object
     * @return String {@link String}
     */
    public static String toJSONString(Object object) {
        if (object == null) {
            return null;
        }
        IfProcess.isNull(jsonFacade,()->init());
        return jsonFacade.toJSONString(object);
    }

    /**
     * 转化为对象
     * @param jsonStr
     * @return
     */
    public static Object parseObject(String jsonStr) {
        return parseObject(jsonStr,Object.class);
    }

    /**
     * 转化为object对象
     * @param jsonStr
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String jsonStr, Class<T> clazz) {
        if (Objects.isBlank(jsonStr)) {
            return null;
        }
        IfProcess.isNull(jsonFacade,()->init());
        return jsonFacade.parseObject(jsonStr,clazz);
    }

    /**
     * 转化为object对象，支持泛型
     * @param jsonStr
     * @param valueTypeRef
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String jsonStr, TypeReference<T> valueTypeRef) {
        if (Objects.isBlank(jsonStr)) {
            return null;
        }
        IfProcess.isNull(jsonFacade,()->init());
        return jsonFacade.parseObject(jsonStr,valueTypeRef);
    }

    /**
     * 转化为object对象
     * @param object
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parseObject(Object object, Class<T> clazz) {
        if (object == null) {
            return null;
        }
        IfProcess.isNull(jsonFacade,()->init());
        return jsonFacade.parseObject(object,clazz);
    }

    /**
     * 转化为object对象，支持泛型
     * @param object
     * @param valueTypeRef
     * @param <T>
     * @return
     */
    public static <T> T parseObject(Object object, TypeReference<T> valueTypeRef) {
        if (object == null) {
            return null;
        }
        IfProcess.isNull(jsonFacade,()->init());
        return jsonFacade.parseObject(object,valueTypeRef);
    }

    /**
     * 转化数组对象
     * @param jsonStr
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> parseArray(String jsonStr, Class<T> clazz) {
        if (Objects.isBlank(jsonStr)) {
            return null;
        }
        IfProcess.isNull(jsonFacade,()->init());
        return jsonFacade.parseArray(jsonStr,clazz);
    }
    //====================== JSONPath ==============================

    /**
     * json读取指定路径数据，支持特定过滤
     * @param object
     * @param jsonPath
     * @param predicates
     * @return
     */
    public static Object read(Object object, String jsonPath, Predicate... predicates) {
        if (object == null || Objects.isBlank(jsonPath)) {
            return null;
        }
        IfProcess.isNull(jsonFacade,()->init());
        return jsonFacade.read(object, jsonPath, predicates);
    }

    /**
     * json读取指定路径数据，支持特定过滤 格式化对象
     * @param object
     * @param jsonPath
     * @param clazz
     * @param predicates
     * @param <T>
     * @return
     */
    public static <T> T read(Object object, String jsonPath, Class<T> clazz, Predicate... predicates) {
        if (object == null || Objects.isBlank(jsonPath)) {
            return null;
        }
        IfProcess.isNull(jsonFacade,()->init());
        return jsonFacade.read(object, jsonPath, clazz, predicates);
    }

    /**
     * json读取指定路径数据 格式化对象（泛型）
     * @param object
     * @param jsonPath
     * @param valueTypeRef
     * @param <T>
     * @return
     */
    public static <T> T read(Object object, String jsonPath, TypeReference<T> valueTypeRef) {
        if (object == null || Objects.isBlank(jsonPath)) {
            return null;
        }
        IfProcess.isNull(jsonFacade,()->init());
        return jsonFacade.read(object, jsonPath, valueTypeRef);
    }

    //====================== JSONRead ==============================
    /**
     * 读取json文件转化为对应类
     * @param path
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T readJson(String path,Class<T> clazz) {
        if (Objects.isBlank(path)) {
            return null;
        }
        IfProcess.isNull(jsonFacade,()->init());
        return jsonFacade.readJson(path,clazz);
    }

    /**
     * 读取json文件转化为对应类
     * @param path
     * @param valueTypeRef
     * @param <T>
     * @return
     */
    public static <T> T readJson(String path,TypeReference<T> valueTypeRef) {
        if (Objects.isBlank(path)) {
            return null;
        }
        IfProcess.isNull(jsonFacade,()->init());
        return jsonFacade.readJson(path,valueTypeRef);
    }

    /**
     * 读取json文件转化为对应类
     * @param path
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> readJsonArray(String path,Class<T> clazz) {
        if (Objects.isBlank(path)) {
            return null;
        }
        IfProcess.isNull(jsonFacade,()->init());
        return jsonFacade.readJsonArray(path,clazz);
    }

    /**
     * 写json到指定文件路径处
     * @param path
     * @param object
     */
    public static void writeJson(String path,Object object){
        if (Objects.isBlank(path) || object == null) {
            return;
        }
        IfProcess.isNull(jsonFacade,()->init());
        jsonFacade.writeJson(path,object);
    }

    /**
     * 写json到指定输出流
     * @param os
     * @param object
     */
    public static void writeJson(OutputStream os, Object object){
        if (object == null) {
            return;
        }
        IfProcess.isNull(jsonFacade,()->init());
        jsonFacade.writeJson(os,object);
    }

}
