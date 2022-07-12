package cn.marcus.json.base.function;

/**
 * 基础回调用处理
 * @author 北城微雨
 * @date 2022/6/8
 */
@FunctionalInterface
public interface Handler {

    /**
     * 基础功能处理
     * @param trueHandler ture回调函数
     * @param falseHandler false回调函数
     */
    void process(Runnable trueHandler,Runnable falseHandler);
}
