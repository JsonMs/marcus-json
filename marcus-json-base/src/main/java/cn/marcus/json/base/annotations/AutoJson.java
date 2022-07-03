package cn.marcus.json.base.annotations;

import java.lang.annotation.*;

/**
 * 自动json注入
 * @author 北城微雨
 * @date 2022/5/27
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoJson {

    /**
     * json类型
     * @return
     */
    String value() default "JACKSON";
}
