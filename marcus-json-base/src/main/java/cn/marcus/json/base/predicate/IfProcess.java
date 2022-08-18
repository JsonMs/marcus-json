package cn.marcus.json.base.predicate;

import cn.marcus.json.base.function.Handler;

import java.util.Collection;
import java.util.function.Consumer;

/**
 * 如果处理判断条件
 *
 * @author 北城微雨
 * @date 2022/5/27
 */
public class IfProcess {

    /**
     * 如果判断条件是true，执行指定函数
     *
     * @param expression 判断条件
     * @param runnable   指定操作
     */
    public static void isTrue(boolean expression, Runnable runnable) {
        if (expression) {
            runnable.run();
        }
    }

    /**
     * 如果判断条件是true，执行指定函数
     *
     * @param expression 判断条件
     * @param runnable   指定操作
     */
    public static void isFalse(boolean expression, Runnable runnable) {
        isTrue(!expression, runnable);
    }


    /**
     * 如果判断对象是Null，执行指定函数
     *
     * @param object   对象
     * @param runnable 指定操作
     */
    public static void isNull(Object object, Runnable runnable) {
        if (object == null) {
            runnable.run();
        }
    }

    /**
     * 如果判断对象不是是Null，执行指定函数
     *
     * @param object   对象
     * @param runnable 运行数据
     */
    public static void nonNull(Object object, Runnable runnable) {
        if (object != null) {
            runnable.run();
        }
    }

    /**
     * 如果判断对象不是是Null，执行指定函数,指定一个入参
     *
     * @param object   对象
     * @param consumer 运行数据
     * @param <T> 泛型
     */
    public static <T> void nonNull(T object, Consumer<T> consumer) {
        if (object != null) {
            consumer.accept(object);
        }
    }

    /**
     * 如果判断集合对象是空集合，执行指定函数
     *
     * @param collection 对象
     * @param runnable   运行数据
     */
    public static void isEmpty(Collection<?> collection, Runnable runnable) {
        if (collection == null || collection.isEmpty()) {
            runnable.run();
        }
    }

    /**
     * 如果判断集合对象不是空集合，执行指定函数
     *
     * @param collection 对象
     * @param runnable   运行数据
     */
    public static void isNotEmpty(Collection<?> collection, Runnable runnable) {
        if (collection != null && !collection.isEmpty()) {
            runnable.run();
        }
    }

    /**
     * 如果判断集合对象是空集合，执行指定函数，入参
     *
     * @param collection 对象
     * @param consumer   运行数据
     */
    public static void isEmpty(Collection<?> collection, Consumer<Collection<?>> consumer) {
        if (collection == null || collection.isEmpty()) {
            consumer.accept(collection);
        }
    }

    /**
     * 如果判断集合对象不是空集合，执行指定函数，入参
     *
     * @param collection 对象
     * @param consumer   运行数据
     */
    public static void isNotEmpty(Collection<?> collection, Consumer<Collection<?>> consumer) {
        if (collection != null && !collection.isEmpty()) {
            consumer.accept(collection);
        }
    }



    /**
     * 多语言表达方式
     *
     * @param expression 表达式
     * @return 回调方法
     */
    public static Handler of(boolean expression) {
        return (trueHandler, falseHandler) -> {
            if (expression) {
                trueHandler.run();
            } else {
                falseHandler.run();
            }
        };
    }

}
