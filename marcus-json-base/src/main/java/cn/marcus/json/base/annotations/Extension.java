package cn.marcus.json.base.annotations;

import java.lang.annotation.*;

/**
 * 扩展接口实现类表识
 * @author 北城微雨
 * @date 2022/6/8
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Extension {

    /**
     * 别名扩展实现名称
     * @return
     */
    String value();
}
