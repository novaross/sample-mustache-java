package com.rs;

import com.rs.modbase.ArtifactDependency;
import com.rs.modbase.ContextModBase;
import com.rs.modbase.ModBase;
import lombok.extern.slf4j.Slf4j;

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
        dependencies.add(new ArtifactDependency("testImplementation", "org.junit.jupiter", "junit-jupiter-api", "5.8.1"));
        dependencies.add(new ArtifactDependency("testImplementation", "org.junit.jupiter", "junit-jupiter-engine", "5.8.1"));
        dependencies.add(new ArtifactDependency("implementation", "org.slf4j", "slf4j-api", "2.0.6"));
        dependencies.add(new ArtifactDependency("implementation", "ch.qos.logback", "logback-classic", "1.4.5"));
        dependencies.add(new ArtifactDependency("compileOnly", "org.projectlombok", "lombok", "1.18.24"));
        dependencies.add(new ArtifactDependency("annotationProcessor", "org.projectlombok", "lombok", "1.18.24"));

        ContextModBase contextModBase = new ContextModBase();
        contextModBase.setBasePackage(contextMain.getBasePackage());
        contextModBase.setGroup(contextMain.getGroup());
        contextModBase.setVersion(contextMain.getVersion());
        contextModBase.setTargetFolder(contextMain.getTargetFolder());
        contextModBase.setDependencies(dependencies);

        ModBase modBase = new ModBase();
        modBase.setContextModBase(contextModBase);
        modBase.process();

    }
}