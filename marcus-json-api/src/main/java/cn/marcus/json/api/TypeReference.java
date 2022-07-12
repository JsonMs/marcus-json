package cn.marcus.json.api;

import cn.marcus.json.api.exception.JsonException;
import cn.marcus.json.base.predicate.Assert;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 抽象类型引用
 * @author 北城微雨
 * @date 2022/5/26
 */
public abstract class TypeReference<T> implements Comparable<TypeReference<T>> {

    /** 类型 */
    private final Type _type;

    /**
     * 构造方法
     */
    protected TypeReference() {
        Type superClass = this.getClass().getGenericSuperclass();
        Assert.isTrue(superClass instanceof Class,()-> JsonException.newEx("内部错误：在没有实际类型信息的情况下构建的TypeReference"));
        this._type = ((ParameterizedType)superClass).getActualTypeArguments()[0];
    }

    /**
     * 获取类型
     * @return 类型
     */
    public Type getType() {
        return this._type;
    }

    /**
     * 比较
     * @param o 对象
     * @return 比较大小
     */
    @Override
    public int compareTo(TypeReference<T> o) {
        return 0;
    }
}
