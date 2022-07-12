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
     * @param object 对象
     * @return json字符串
     */
    String toJSONString(Object object);

    /**
     * 转化为对象
     * @param object 对象
     * @param clazz 格式化类
     * @param <T> 泛型
     * @return 格式化对象
     */
    <T> T parseObject(Object object,Class<T> clazz);

    /**
     * 转化为对象
     * @param object 对象
     * @param valueTypeRef 格式化类型
     * @param <T> 泛型
     * @return 格式化对象
     */
    <T> T parseObject(Object object, TypeReference<T> valueTypeRef);

    /**
     * 转化为对象
     * @param jsonStr 字符串
     * @param clazz 格式化类
     * @param <T> 泛型
     * @return 格式化对象
     */
    <T> T parseObject(String jsonStr,Class<T> clazz);

    /**
     * 转化为对象
     * @param jsonStr 字符串
     * @param valueTypeRef 格式化类型
     * @param <T> 泛型
     * @return 格式化对象
     */
    <T> T parseObject(String jsonStr, TypeReference<T> valueTypeRef);



    /**
     * 转化为对象数组
     * @param jsonStr 字符串
     * @param clazz 格式化类
     * @param <T> 泛型
     * @return 格式化数组
     */
    <T> List<T> parseArray(String jsonStr, Class<T> clazz);

    /**
     * 读取json文件转化为对应类
     * @param path 路径
     * @param clazz 格式化类
     * @param <T> 泛型
     * @return 格式化对象
     */
    <T> T readJson(String path,Class<T> clazz);

    /**
     * 读取json文件转化对应类（泛型）
     * @param path 路径
     * @param valueTypeRef 格式化类型
     * @param <T> 泛型
     * @return 格式化对象
     */
    <T> T readJson(String path,TypeReference<T> valueTypeRef);

    /**
     * 读取json文件数组
     * @param path 路径
     * @param clazz 格式化类
     * @param <T> 泛型
     * @return 格式化数组
     */
    <T> List<T> readJsonArray(String path,Class<T> clazz);

    /**
     * 写到json文件中
     * @param path 路径
     * @param object 写入数据
     */
    void writeJson(String path,Object object);

    /**
     * 数据写到json文件中
     * @param os 输出流
     * @param object 写入数据
     */
    void writeJson(OutputStream os, Object object);

    /**
     * 按jsonPath或者指定过滤读取数据
     * @param json json对象
     * @param jsonPath json路径
     * @param predicates 指定过滤条件
     * @return
     */
    Object read(Object json, String jsonPath, Predicate... predicates);

    /**
     * 按jsonPath或者指定过滤读取数据格式化
     * @param json json对象
     * @param jsonPath json路径
     * @param clazz 格式化类
     * @param predicates 判断条件
     * @param <T> 泛型
     * @return 读取对象
     */
    <T> T read(Object json, String jsonPath,Class<T> clazz,Predicate... predicates);

    /**
     * 按jsonPath或者指定过滤读取数据格式化（泛型）
     * @param json json对象
     * @param jsonPath json路径
     * @param valueTypeRef 格式化类型
     * @param <T> 泛型
     * @return 读取对象
     */
    <T> T read(Object json, String jsonPath,TypeReference<T> valueTypeRef);

    /**
     * 按jsonPath设置值
     * @param jsonObject json对象
     * @param path json路径
     * @param object 设置值
     */
    void set(Object jsonObject,String path,Object object);
}
