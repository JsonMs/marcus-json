package cn.marcus.json.base.predicate;

import java.util.Collection;
import java.util.function.Supplier;

/**
 * 断言基础判断类
 *
 * @author 北城微雨
 * @date 2022/4/24
 */
public class Assert {

    /**
     * 如果判断条件是true，抛出指定异常信息
     *
     * @param expression         判断条件
     * @param supplier 指定异常信息
     * @param <E>                指定异常信息
     */
    public static <E extends RuntimeException> void isTrue(boolean expression, Supplier<E> supplier) {
        if (expression) {
            throw supplier.get();
        }
    }

    /**
     * 如果判断条件是true，抛出指定异常信息
     *
     * @param expression         判断条件
     * @param supplier 指定异常信息
     * @param <E>
     */
    public static <E extends RuntimeException> void isFalse(boolean expression, Supplier<E> supplier) {
        isTrue(!expression, supplier);
    }

    /**
     * 如果判断对象是null，抛出指定异常信息
     *
     * @param object             判断对象
     * @param supplier 指定异常信息
     * @param <E>
     */
    public static <E extends RuntimeException> void isNull(Object object, Supplier<E> supplier) {
        if (object == null) {
            throw supplier.get();
        }
    }

    /**
     * 如果判断对象不是是null，抛出指定异常信息
     *
     * @param object             判断对象
     * @param supplier 指定异常信息
     * @param <E>
     */
    public static <E extends RuntimeException> void nonNull(Object object, Supplier<E> supplier) {
        if (object != null) {
            throw supplier.get();
        }
    }

    /**
     * 如果判断集合是空集合，抛出指定异常信息
     *
     * @param collection 判断集合
     * @param supplier   指定异常信息
     * @param <E>
     */
    public static <E extends RuntimeException> void isEmpty(Collection<?> collection, Supplier<E> supplier) {
        if (collection == null || collection.isEmpty()) {
            throw supplier.get();
        }
    }

    /**
     * 如果判断集合不是空集合，抛出指定异常信息
     *
     * @param collection 判断集合
     * @param supplier 指定异常信息
     * @param <E>
     */
    public static <E extends RuntimeException> void isNotEmpty(Collection<?> collection, Supplier<E> supplier) {
        if (collection != null && !collection.isEmpty()) {
            throw supplier.get();
        }
    }

}
