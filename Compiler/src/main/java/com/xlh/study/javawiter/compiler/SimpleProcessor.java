package com.xlh.study.javawiter.compiler;

import com.google.auto.service.AutoService;

import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.JavaFileObject;

@AutoService(Processor.class)
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class SimpleProcessor extends AbstractProcessor {

    // Elements中包含用于操作Element的工作方法
    private Elements elementUtils;
    // Filer用来创建新的源文件，class文件以及辅助文件
    private Filer filer;
    // 打印使用
    private Messager messager;
    // Types包含用于操作TypeMirror的工具方法
    private Types typeUtils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        elementUtils = processingEnvironment.getElementUtils();
        filer = processingEnvironment.getFiler();
        messager = processingEnvironment.getMessager();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> types = new LinkedHashSet<>();
        types.add(Override.class.getCanonicalName());
        return types;
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        sampleWriter();

        return true;


    }


    private void sampleWriter() {
        try {
            // 包名，同app-module包名
            String packageName = "com.xlh.study.javawriter";
            // 创建一个新的源文件（class），并返回一个对象可以写入
            JavaFileObject javaFileObject = filer.createSourceFile(packageName+".GenerateJavaWriter");
            Writer writer = javaFileObject.openWriter();
            // 第一行生成包
            writer.write("package " + packageName + ";\n\n");
            // 第二行生成要导入的类（必须手动导入）
            writer.write("import android.util.Log;\n\n");
            // 第三行生成类名
            writer.write("public class GenerateJavaWriter {\n");

            // 生成代码
            writer.write("  String str = \"Hello Java Writer\";\n\n");
            writer.write("  public void say() {\n");
            writer.write("      Log.e(\"TAG\", \"say:\" + str);\n");
            writer.write("  }\n");

            writer.write("  public void testFor(){\n");
            writer.write("      for (int i = 0; i < 10; i++) {\n");
            writer.write("          Log.e(\"TAG\", \"i--->\" + i);");
            writer.write("      }\n");
            writer.write("  }\n");

            writer.write("}\n");

            writer.flush();
            // 最后关闭
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
