<!-- Add buttons here -->

<p align="center">
  <img width="70%" src="https://user-images.githubusercontent.com/50287117/177046687-61329ce2-d361-46d6-80b2-eb1fd9b4cef3.png" align="center" alt="Marcus Json" />
</p>

<p align="center">
    <a href="https://github.com/JsonMs/marcus-json/releases">
      <img src="https://img.shields.io/badge/ReleaseTag-1.0.1-gree"/>
    </a>
    <a href="https://search.maven.org/artifact/io.github.jsonms/marcus-json-spring-boot-starter/1.0.1/jar">
      <img src="https://img.shields.io/badge/Release Springboot-1.0.1-gree"/>
    </a>
    <a href="https://search.maven.org/artifact/io.github.jsonms/marcus-json-api/1.0.1/jar">
      <img src="https://img.shields.io/badge/Release Base API-1.0.1-gree"/>
    </a>
     <a href="https://github.com/JsonMs/marcus-json">
      <img alt="JDK" src="https://img.shields.io/badge/JDK-8-blue??style=flat&logo=java&color=0088ff" />
    </a>
    <a href="https://github.com/JsonMs/marcus-json">
      <img alt="MAVEN" src="https://img.shields.io/badge/maven-3.6-red?style=flat&logo=apachemaven&color=0088ff" />
    </a>
    <a href="https://github.com/JsonMs/marcus-json">
      <img alt="Spring Boot" src="https://img.shields.io/badge/springboot-2.4.5-gree?style=flat&logo=springboot" />
    </a>
</p>

  本功能希望实现json方式的统一管理和扩展和灵活一键切换功能，简化接入方式和打通各个json的隔阂，实现类似type-c接口一样的通用能力
对于已经接入其他json的，可以先使用对应json类型的实现替换原来的方法，然后实现丝滑切换不同的json类型方式，也支持扩展自己的json类型
<h2/>

# 如何添加
基础组建功能版本api，封装了对象、数组、json文件、json路径功能，支持自定义扩展json组件能力

Step1: 导入功能模块

基础api版本
```xml
<dependency>
    <groupId>io.github.jsonms</groupId>
    <artifactId>marcus-json-api</artifactId>
    <version>1.0.1</version>
</dependency>
```
springboot 启动器版本
```xml
<dependency>
    <groupId>io.github.jsonms</groupId>
    <artifactId>marcus-json-spring-boot-starter</artifactId>
    <version>1.0.1</version>
</dependency>
```
Step2: 指定使用的json类型 (可选)

只能在一个类中使用，如果多个类会有相应提示信息，默认使用jackson

`@AutoJson(JsonEnum.JACKSON)`

或者
```yaml
spring:
    json:
      type: jackson
```

# 扩展功能
借鉴其他优秀框架的spi能力，支持spi的文件和注解配置，实现类可以基于如下两个类扩展

原始接口：`cn.marcus.json.api.JsonFacade`

抽象类（自实现jsonpath、json Reader/Write功能）：
`cn.marcus.json.api.AbstractJsonFacade`

方式1：注解(推荐)

使用`@Extension("jackson")`放到实现类上面，括号填入json类型别名即可

方式2：文件配置

需要在在resources下新建META-INF/services/json/，建文件
cn.marcus.json.api.JsonFacade如图

```
├── resources
    └── META-INF
        └── service
            └── json
                └── cn.marcus.json.api.JsonFacade
```

文件名cn.marcus.json.api.JsonFacade文件里面格式

扩展json类型:扩展实现类，中间通过`=`区分开 如下
```text
fastjson2=cn.marcus.json.api.support.FastJson2Handler
```




