package cn.marcus.json.api.spi;

import cn.marcus.json.base.predicate.Assert;
import lombok.SneakyThrows;

import java.lang.reflect.Constructor;

/**
 * 扩展实现类包装类
 * @author 北城微雨
 * @date 2022/6/8
 */
public class ExtensionClass<T> {
    /**
     * 扩展实现类
     */
    private Class<? extends T> clazz;

    /**
     * 构造方法
     * @param clazz
     */
    public ExtensionClass(Class<? extends T> clazz) {
        this.clazz = clazz;
    }

    /**
     * 调用无参构造器创建扩展实现类实例
     * @return 扩展实现类实例
     */
    @SneakyThrows
    public T getExtInstance(){
        Assert.isNull(clazz,()->new RuntimeException("Class of ExtensionClass is null"));
        return clazz.newInstance();
    }

    @SneakyThrows
    public T getExtInstance(Class[] argTypes, Object[] args){
        Assert.isNull(clazz,()->new RuntimeException("Class of ExtensionClass is null"));
        Constructor<? extends T> constructor = clazz.getDeclaredConstructor(argTypes);
        return constructor.newInstance(args);
    }
}
