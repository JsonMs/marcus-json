package cn.marcus.json.api;

import com.jayway.jsonpath.Predicate;

import java.io.OutputStream;
import java.util.List;

/**
 * json外部接口
 * @author 北城微雨
 * @date 2022/5/25
 */
public interface JsonFacade {

    /**
     * 转化为json字符串
     * @param object
     * @return
     */
    String toJSONString(Object object);

    /**
     * 转化为对象
     * @param object
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T parseObject(Object object,Class<T> clazz);

    /**
     * 转化为对象
     * @param object
     * @param valueTypeRef
     * @param <T>
     * @return
     */
    <T> T parseObject(Object object, TypeReference<T> valueTypeRef);

    /**
     * 转化为对象
     * @param jsonStr
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T parseObject(String jsonStr,Class<T> clazz);

    /**
     * 转化为对象
     * @param jsonStr
     * @param valueTypeRef
     * @param <T>
     * @return
     */
    <T> T parseObject(String jsonStr, TypeReference<T> valueTypeRef);



    /**
     * 转化为对象数组
     * @param jsonStr
     * @param clazz
     * @param <T>
     * @return
     */
    <T> List<T> parseArray(String jsonStr, Class<T> clazz);

    /**
     * 读取json文件转化为对应类
     * @param path
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T readJson(String path,Class<T> clazz);

    /**
     * 读取json文件转化对应类（泛型）
     * @param path
     * @param valueTypeRef
     * @param <T>
     * @return
     */
    <T> T readJson(String path,TypeReference<T> valueTypeRef);

    /**
     * 读取json文件数组
     * @param path
     * @param clazz
     * @param <T>
     * @return
     */
    <T> List<T> readJsonArray(String path,Class<T> clazz);

    /**
     * 写到json文件中
     * @param path
     * @param object
     */
    void writeJson(String path,Object object);

    /**
     * 数据写到json文件中
     * @param os
     * @param object
     */
    void writeJson(OutputStream os, Object object);

    /**
     * 按jsonPath或者指定过滤读取数据
     * @param json
     * @param jsonPath
     * @param predicates
     * @return
     */
    Object read(Object json, String jsonPath, Predicate... predicates);

    /**
     * 按jsonPath或者指定过滤读取数据格式化
     * @param json
     * @param jsonPath
     * @param clazz
     * @param predicates
     * @param <T>
     * @return
     */
    <T> T read(Object json, String jsonPath,Class<T> clazz,Predicate... predicates);

    /**
     * 按jsonPath或者指定过滤读取数据格式化（泛型）
     * @param json
     * @param jsonPath
     * @param valueTypeRef
     * @param <T>
     * @return
     */
    <T> T read(Object json, String jsonPath,TypeReference<T> valueTypeRef);

    /**
     * 按jsonPath设置值
     * @param jsonObject
     * @param path
     * @param object
     */
    void set(Object jsonObject,String path,Object object);
}
