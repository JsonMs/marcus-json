package cn.marcus.json.api.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * json自定义异常
 * @author 北城微雨
 * @date 2022/5/25
 */
@Getter
@NoArgsConstructor
public class JsonException extends RuntimeException{

    /**
     * API构造方法
     * @param message 消息
     */
    public JsonException(String message){
        super(message);
    }

    /**
     * 新建一个异常（语法糖）
     * @param message 消息
     * @return 业务异常
     */
    public static JsonException newEx(String message) {
        return new JsonException(message);
    }
}
