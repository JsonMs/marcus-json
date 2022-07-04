package cn.marcus.json.starter;

import cn.marcus.json.api.JsonUtil;
import cn.marcus.json.base.predicate.IfProcess;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * json基础配置信息
 *
 * @author 北城微雨
 * @date 2022/6/5
 */
@Configuration
@ConditionalOnProperty(name = "spring.json.type")
@EnableConfigurationProperties(JsonProperties.class)
public class JsonConfig {

    /**
     * json配置文件信息
     */
    @Resource
    private JsonProperties jsonProperties;

    /**
     * json初始化数据
     */
    @PostConstruct
    public void init() {
        IfProcess.nonNull(jsonProperties.getType(),
                () -> IfProcess.of(jsonProperties.getPackages() == null)
                        .process(
                                () -> JsonUtil.init(jsonProperties.getType().toUpperCase()),
                                () -> JsonUtil.init(jsonProperties.getType().toUpperCase(),
                                        jsonProperties.getPackages().
                                                toArray(new String[jsonProperties.getPackages().size()]))));
    }
}
