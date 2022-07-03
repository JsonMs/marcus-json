package cn.marcus.json.base.predicate;

/**
 * 基础对象增强
 * @author 北城微雨
 * @date 2022/5/25
 */
public class Objects {

    /**
     * 如果为null填充默认值
     * @param object
     * @param defaultValue
     * @param <T>
     * @return
     */
    public static <T> T defaultIfNull(T object, T defaultValue) {
        return object != null ? object : defaultValue;
    }

    /**
     * 字符串不为空
     * @param cs
     * @return
     */
    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }


    /**
     * 字符串为空
     * @param cs
     * @return
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
     * @param cs
     * @return
     */
    public static int length(CharSequence cs) {
        return cs == null ? 0 : cs.length();
    }
}
