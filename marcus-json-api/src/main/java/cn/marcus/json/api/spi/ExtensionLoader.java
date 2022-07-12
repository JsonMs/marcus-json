package cn.marcus.json.api.spi;

import cn.marcus.json.base.annotations.Extension;
import cn.marcus.json.base.predicate.Assert;
import cn.marcus.json.base.predicate.IfProcess;
import cn.marcus.json.base.predicate.Objects;
import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 扩展实现加载器
 *
 * @author 北城微雨
 * @date 2022/6/8
 */
@Slf4j
public class ExtensionLoader<T> {

    /**
     * 扩展接口名名称
     */
    private String interfaceName;
    /**
     * 扩展接口的所有实现
     */
    private Map<String, ExtensionClass<T>> aliasExtensionCollector;
    /**
     * 排除类名称
     */
    private List<String> exclusionClassNames;

    /**
     * 构造方法
     *
     * @param clazz
     */
    public ExtensionLoader(Class<T> clazz, List<String> exclusionClassNames, String... scanPaths) {
        this.interfaceName = clazz.getName();
        this.aliasExtensionCollector = new ConcurrentHashMap<>();
        this.exclusionClassNames = Objects.defaultIfNull(exclusionClassNames, new ArrayList<>());
        //此处只指定了一个spi文件存储的路径
        load4File("META-INF/services/json/");
        load4ClassLoader(clazz, Lists.newArrayList(scanPaths));
    }

    /**
     * 获取全部的搜集器
     *
     * @return
     */
    public Map<String, ExtensionClass<T>> getAllCollector() {
        return aliasExtensionCollector;
    }

    /**
     * 加载类目
     *
     * @param classLoader 类加载器
     * @param scanPaths 扫描路径
     */
    private void load4ClassLoader(Class<T> classLoader, List<String> scanPaths) {
        IfProcess.isEmpty(scanPaths, IfProcess::abort);
        Reflections reflections = new Reflections(scanPaths, this.getClass().getClassLoader());
        Set<Class<? extends T>> classes = reflections.getSubTypesOf(classLoader);
        for (Class<? extends T> clazz : classes) {
            if (exclusionClassNames.contains(clazz.getName())) {
                continue;
            }
            Extension extension = clazz.getAnnotation(Extension.class);
            Assert.isTrue(extension == null || Objects.isBlank(extension.value()), () -> new RuntimeException(String.format("%s need @Extension", clazz.getName())));
            ExtensionClass<T> extensionClass = new ExtensionClass<>(clazz);
            aliasExtensionCollector.putIfAbsent(extension.value().toUpperCase(), extensionClass);
        }
    }

    @SneakyThrows
    private void load4File(String configPath) {
        String spiFile = configPath.concat(interfaceName);
        ClassLoader classLoader = this.getClass().getClassLoader();
        load4ClassLoader(classLoader, spiFile);
    }

    @SneakyThrows
    private void load4ClassLoader(ClassLoader classLoader, String spiFile) {
        //读取多个spi文件
        Enumeration<URL> urls = classLoader != null ? classLoader.getResources(spiFile) : ClassLoader.getSystemResources(spiFile);
        IfProcess.isNull(urls, IfProcess::abort);
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), Charset.defaultCharset()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    //读取文件中的每一行
                    readLine(line);
                }
            } catch (Exception e) {
                log.error("load {} fail ", spiFile, e);
            }
        }
    }

    /**
     * 读取一行数据，并填充到收集器中
     *
     * @param line 一行数据
     * @throws ClassNotFoundException 类不存在异常
     */
    private void readLine(String line) throws ClassNotFoundException {
        String[] aliasClassName = line.split("=");
        IfProcess.isTrue(aliasClassName == null || aliasClassName.length != 2, IfProcess::abort);
        String alias = aliasClassName[0].trim();
        String className = aliasClassName[1].trim();
        Class<?> clazz = Class.forName(className, false, this.getClass().getClassLoader());

        Extension annotation = clazz.getAnnotation(Extension.class);
        IfProcess.isNull(annotation, () -> {
            log.error("{} need @Extension", className);
            return;
        });
        ExtensionClass<T> extensionClass = new ExtensionClass<>((Class<? extends T>) clazz);
        aliasExtensionCollector.putIfAbsent(alias.toUpperCase(), extensionClass);
    }

    /**
     * 通过别名获取相应实例(无参)
     *
     * @param alias 别名
     * @return 无参的实例
     */
    public T getExtension(String alias) {
        ExtensionClass<T> extensionClass = aliasExtensionCollector.get(alias);
        Assert.isNull(extensionClass, () -> new RuntimeException(String.format("Not found extension of %s named: %s ", interfaceName, alias)));
        return extensionClass.getExtInstance();
    }

    /**
     * 通过别名获取相应的实例(有参)
     *
     * @param alias 别名
     * @param argTypes 参数类型
     * @param args  参数
     * @return 有参的实例
     */
    public T getExtension(String alias, Class[] argTypes, Object[] args) {
        ExtensionClass<T> extensionClass = aliasExtensionCollector.get(alias);
        Assert.isNull(extensionClass, () -> new RuntimeException(String.format("Not found extension of %s named: %s ", interfaceName, alias)));
        return extensionClass.getExtInstance(argTypes, args);
    }


}
