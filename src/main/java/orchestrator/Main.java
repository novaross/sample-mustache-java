package orchestrator;

import helper.TemplateWriter;
import lombok.extern.slf4j.Slf4j;
import modbase.ArtifactDependency;
import modbase.ContextModBase;
import modbase.ModBase;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Main {

    public static void main(String[] args) {

        ContextMain contextMain = new ContextMain();
        contextMain.setBasePackage("com.example.myapp");
        contextMain.setGroup("com.example");
        contextMain.setVersion("1.0-SNAPSHOT");
        contextMain.setTargetFolder("c:/temp/generated");
        List<ArtifactDependency> dependencies = new ArrayList<>();

        dependencies.add(new ArtifactDependency("testCompile", "junit", "junit", "4.12"));
        dependencies.add(new ArtifactDependency("testCompile", "com.github.spullara.mustache.java", "compiler", "0.8.9"));
        dependencies.add(new ArtifactDependency("implementation", "com.google.code.gson", "gson", "2.8.6"));

        ContextModBase contextModBase = new ContextModBase();

        contextModBase.setBasePackage(contextMain.getBasePackage());
        contextModBase.setGroup(contextMain.getGroup());
        contextModBase.setVersion(contextMain.getVersion());
        contextModBase.setTargetFolder(contextMain.getTargetFolder());
        contextModBase.setDependencies(dependencies);

        ModBase modBase = new ModBase();
        modBase.setContextModBase(contextModBase);
        modBase.process();
        String path="c:/temp/generated/src/main/java/basepackage/helper/FileOperations.java";
        log.debug("Replaced: {}", path.replace("basepackage","com/example"));


    }

}
