package cn.marcus.json.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * json基础配置属性信息
 *
 * @author jinmai
 * @date 2022/6/5
 */
@ConfigurationProperties(prefix = "spring.json")
public class JsonProperties {

    /**
     * json类型
     */
    private String type;
    /**
     * 扫描包
     */
    private List<String> packages;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getPackages() {
        return packages;
    }

    public void setPackages(List<String> packages) {
        this.packages = packages;
    }
}
