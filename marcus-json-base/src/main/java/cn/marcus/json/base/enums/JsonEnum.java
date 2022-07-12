package cn.marcus.json.base.enums;

/**
 * json枚举
 * @author 北城微雨
 * @date 2022/5/25
 */
public enum JsonEnum {
    JACKSON("com.fasterxml.jackson.databind.ObjectMapper"),
    FASTJSON("com.alibaba.fastjson.JSON");

    /** 基础类型 */
    private String clazz;

    /***
     * 构造方法
     * @param clazz 类型
     */
    JsonEnum(String clazz) {
        this.clazz = clazz;
    }

    public String getClazz() {
        return clazz;
    }
}
