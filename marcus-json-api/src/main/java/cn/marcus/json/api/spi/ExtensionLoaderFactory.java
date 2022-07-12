package cn.marcus.json.api.spi;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 扩展加载器工厂
 * @author 北城微雨
 * @date 2022/6/8
 */
public class ExtensionLoaderFactory {

    /**
     * <K,V> -> <扩展接口Class,扩展接口对应加载器>
     */
    private static final Map<Class, ExtensionLoader> LOADER_MAP = new ConcurrentHashMap<>();

    /**
     * 获取或创建 clazz扩展接口的 {@link ExtensionLoader}
     *
     * @param clazz 扩展接口
     * @param <T> 泛型
     * @return 扩展接口的 {@link ExtensionLoader}
     */
    public static <T> ExtensionLoader<T> getExtensionLoader(Class<T> clazz, List<String> exclusionClassNames, String... scanPaths) {
        ExtensionLoader loader = LOADER_MAP.get(clazz);
        if (loader == null) {
            synchronized (ExtensionLoaderFactory.class) {
                if (loader == null) {
                    loader = new ExtensionLoader(clazz,exclusionClassNames, scanPaths);
                    LOADER_MAP.put(clazz, loader);
                }
            }
        }
        return loader;
    }
}
