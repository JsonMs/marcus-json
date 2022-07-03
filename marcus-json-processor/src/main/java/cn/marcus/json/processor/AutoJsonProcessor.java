package cn.marcus.json.processor;

import cn.marcus.json.base.annotations.AutoJson;
import cn.marcus.json.base.predicate.IfProcess;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.Writer;
import java.util.Set;

/**
 * 自动json解析器
 *
 * @author 北城微雨
 * @date 2022/5/27
 */
@SupportedAnnotationTypes(
        "cn.marcus.json.base.annotations.AutoJson")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedOptions("debug")
@AutoService(Processor.class)
public class AutoJsonProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        this.log("JsonConfig init process start ...");
        this.log("JsonConfig round env:" + roundEnv.toString());
        this.log("JsonConfig annotations:" + annotations);
        for (TypeElement annotation : annotations) {
            Set<? extends Element> annotatedElements = roundEnv.getElementsAnnotatedWith(annotation);
            IfProcess.isTrue(annotatedElements.size() > 1, () -> logError("@AutoJson can only be set once"));
            Element element = annotatedElements.stream().findFirst().get();
            this.log(element.toString());
            AutoJson autoJson = element.getAnnotation(AutoJson.class);
            this.logWarning("JsonConfig jsonType of autoJson is " + autoJson.value());
            String className = element.toString();
            writeJava(className, autoJson.value());
        }
        this.log("JsonConfig init process end!");
        return true;
    }

    /**
     * 打印日志错误信息
     *
     * @param message
     */
    private void logError(String message) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, message);
    }

    /**
     * 打印日志警告信息
     *
     * @param message
     */
    private void logWarning(String message) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, "WARNING:" + message);
    }

    /**
     * 打印日志信息
     *
     * @param message
     */
    private void log(String message) {
        this.processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, message);
    }


    @SneakyThrows
    private void writeJava(String className, String jsonType) {
        System.out.println("ClassName:" + className);
        System.out.println("jsonType:" + jsonType);
        String packageName = null;
        int lastDot = className.lastIndexOf('.');
        if (lastDot > 0) {
            packageName = className.substring(0, lastDot);
        }

        String newSimpleClassName = "JsonConfig";
        String newClassName = packageName.concat(".").concat(newSimpleClassName);

        JavaFileObject createFile = processingEnv.getFiler().createSourceFile(newClassName);

        try (Writer writer = createFile.openWriter()) {
            MethodSpec initMethod = MethodSpec.methodBuilder("init")
                    .addAnnotation(PostConstruct.class)
                    .addModifiers(Modifier.PUBLIC)
                    .addStatement("$T.init($S)", Class.forName("cn.marcus.json.api.JsonUtil"), jsonType)
                    .build();

            TypeSpec clazz = TypeSpec.classBuilder(newSimpleClassName)
                    .addAnnotation(Configuration.class)
                    .addModifiers(Modifier.PUBLIC)
                    .addMethod(initMethod)
                    .build();

            JavaFile javaFile = JavaFile.builder(packageName, clazz)
                    .build();
            javaFile.writeTo(writer);
        }
    }
}
