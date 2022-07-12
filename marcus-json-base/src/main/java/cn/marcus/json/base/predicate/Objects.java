package cn.marcus.json.base.predicate;

/**
 * 基础对象增强
 * @author 北城微雨
 * @date 2022/5/25
 */
public class Objects {

    /**
     * 如果为null填充默认值
     * @param object 基础对象
     * @param defaultValue 默认值
     * @param <T> 泛型
     * @return 指定类型数据
     */
    public static <T> T defaultIfNull(T object, T defaultValue) {
        return object != null ? object : defaultValue;
    }

    /**
     * 字符串不为空
     * @param cs 字符串
     * @return 判断字符串是否不为空
     */
    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }


    /**
     * 字符串为空
     * @param cs 字符串
     * @return 判断字符串是否为空
     */
    public static boolean isBlank(CharSequence cs) {
        int strLen = length(cs);
        if (strLen == 0) {
            return true;
        } else {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }

            return true;
        }
    }

    /**
     * 字符长度
     * @param cs 字符串
     * @return 字符串的长度
     */
    public static int length(CharSequence cs) {
        return cs == null ? 0 : cs.length();
    }
}
