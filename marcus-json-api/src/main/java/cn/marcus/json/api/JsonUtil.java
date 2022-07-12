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
     * @param jsonType json类型
     * @param scanPackage 扫描包地址
     */
    public static void init(String jsonType, String... scanPackage) {
        jsonFacade = JsonFactory.init(jsonType, scanPackage);
    }

    /**
     * 转化为json字符串
     * @param object 格式化对象
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
     * @param jsonStr json字符串
     * @return 格式化对象
     */
    public static Object parseObject(String jsonStr) {
        return parseObject(jsonStr,Object.class);
    }

    /**
     * 转化为object对象
     * @param jsonStr json字符串
     * @param clazz 格式化类
     * @param <T> 泛型
     * @return 格式化对象
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
     * @param jsonStr json字符串
     * @param valueTypeRef 格式化类型
     * @param <T> 泛型
     * @return 格式化对象
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
     * @param object 对象
     * @param clazz 格式化类
     * @param <T> 泛型
     * @return 格式化类
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
     * @param object 对象
     * @param valueTypeRef 格式化类型
     * @param <T> 泛型
     * @return 格式化对象
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
     * @param jsonStr json字符串
     * @param clazz 格式化类
     * @param <T> 泛型
     * @return 格式化数组
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
     * @param object 格式化对象
     * @param jsonPath json路径
     * @param predicates 过滤条件
     * @return 格式化对象
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
     * @param object 格式化对象
     * @param jsonPath json路径
     * @param clazz 格式化类
     * @param predicates 过滤条件
     * @param <T> 泛型
     * @return 格式化对象
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
     * @param object 对象
     * @param jsonPath json路径
     * @param valueTypeRef 格式化类型
     * @param <T> 泛型
     * @return 格式化对象
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
     * @param path json文件路径
     * @param clazz 格式化对象类
     * @param <T> 泛型
     * @return 格式化对象
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
     * @param path json路径
     * @param valueTypeRef 格式化类型
     * @param <T> 泛型
     * @return 格式化对象
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
     * @param path json文件路径
     * @param clazz 格式化类
     * @param <T> 泛型
     * @return 格式化数组
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
     * @param path 写入json文件路径
     * @param object 写入数据
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
     * @param os 输出对象
     * @param object 数据
     */
    public static void writeJson(OutputStream os, Object object){
        if (object == null) {
            return;
        }
        IfProcess.isNull(jsonFacade,()->init());
        jsonFacade.writeJson(os,object);
    }

}
