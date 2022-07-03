package cn.marcus.json.base.function;

/**
 * Represents a supplier.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #get()}.
 * @since 1.8
 *
 * @author 北城微雨
 * @date 2022/5/27
 */
@FunctionalInterface
public interface BiSupplier {


    /**
     * 获取返回结果
     */
    void get();
}
