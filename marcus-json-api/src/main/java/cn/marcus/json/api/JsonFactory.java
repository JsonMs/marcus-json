package cn.marcus.json.api;

import cn.marcus.json.api.exception.JsonException;
import cn.marcus.json.api.spi.ExtensionClass;
import cn.marcus.json.api.spi.ExtensionLoader;
import cn.marcus.json.api.spi.ExtensionLoaderFactory;
import cn.marcus.json.api.support.FastJsonHandler;
import cn.marcus.json.api.support.JackSonHandler;
import cn.marcus.json.base.enums.JsonEnum;
import cn.marcus.json.base.predicate.Assert;
import cn.marcus.json.base.predicate.IfProcess;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * json工厂
 *
 * @author 北城微雨
 * @date 2022/6/1
 */
@Slf4j
public class JsonFactory {

    /**
     * 工厂
     */
    private final static Map<String, Supplier<JsonFacade>> FACTORY = new HashMap<>(16);

    /**
     * 工厂加载
     */
    public static void load(String... scanPackage) {
        FACTORY.put(JsonEnum.JACKSON.toString(), JackSonHandler::new);
        FACTORY.put(JsonEnum.FASTJSON.toString(), FastJsonHandler::new);
        ExtensionLoader<JsonFacade> extensionLoader = ExtensionLoaderFactory.getExtensionLoader(JsonFacade.class, Lists.newArrayList(AbstractJsonFacade.class.getName()), scanPackage);
        if (extensionLoader != null) {
            Map<String, ExtensionClass<JsonFacade>> collector = extensionLoader.getAllCollector();
            IfProcess.isNull(collector, IfProcess::abort);
            collector.entrySet().forEach(
                    entry -> FACTORY.put(entry.getKey(),
                            () -> entry.getValue().getExtInstance()));
        }
    }

    /**
     * 数据初始化
     *
     * @param jsonType json类型
     * @return json工厂
     */
    public static JsonFacade init(String jsonType, String... scanPackage) {
        load(scanPackage);
        Assert.isNull(FACTORY.keySet().contains(jsonType), () -> JsonException.newEx(String.format("[JsonUtil] 不支持当前json类型：%s", jsonType)));
        JsonEnum jsonEnum = null;
        try {
            jsonEnum = JsonEnum.valueOf(jsonType);
        } catch (Exception e) {
            log.warn("[JsonUtil] 内部实现没有这个类型:{}",jsonType);
        }
        IfProcess.nonNull(jsonEnum, json -> warmUp(json.getClazz()));
        return createJson(jsonType);
    }

    /**
     * 预热json类实例
     *
     * @param className 类名称
     */
    private static void warmUp(String className) {
        try {
            Class.forName(className);
        } catch (Throwable e) {
            throw JsonException.newEx(String.format("[JsonUtil] json实例缺少相关引用依赖 [%s] ==> ", className).concat(e.getMessage()));
        }
    }

    /**
     * 创建json接口
     *
     * @param jsonType json类型
     * @return json工厂
     */
    private static JsonFacade createJson(String jsonType) {
        Supplier<JsonFacade> supplier = FACTORY.get(jsonType);
        return supplier.get();
    }
}
