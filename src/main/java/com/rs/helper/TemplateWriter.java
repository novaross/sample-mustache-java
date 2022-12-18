package com.rs.helper;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Ruslan Spivak
 */
@Data
@Slf4j
public class TemplateWriter {

    // private ContextModBase contextModBase;
    private Map<String, Object> templateContextMap;
    private String templateFolder;
    private String targetFolder;

    public void process() {
        List<File> files = listAllFiles(templateFolder);
        files.forEach(file -> {
            String sourceFile = file.getAbsolutePath();
            int indexOfTemplateFolder = sourceFile.indexOf(templateFolder);
            String basePackageFolder = basePackageToFolder(templateContextMap.get(HelperConstants.BASE_PACKAGE_PROPERTY).toString());
            // construct target file path from the target folder and the relative path of the original file
            String targetFile = targetFolder + sourceFile.substring(indexOfTemplateFolder + templateFolder.length());
            // make the target file path consistent
            targetFile = targetFile.replace('\\', '/');
            // expand the base package folder in the template folder to path according to the provided base package
            targetFile = targetFile.replace(HelperConstants.BASE_PACKAGE_PLACEHOLDER, basePackageFolder);
            log.debug("targetFile: {}", targetFile);
            if (FileOperations.isMatchExtension(sourceFile, HelperConstants.TEMPLATE_EXTENSION)) {
                // this is a template file, process and save to target location
                targetFile = FileOperations.removeFileExtension(targetFile);
                log.debug("Source File: {}, Target File: {}", sourceFile, targetFile);
                String content = compileMustacheTemplate(sourceFile, templateContextMap);
                saveContentAsFile(targetFile, content);
            } else {
                // this is a regular file, just copy it to the target location
                try {
                    FileOperations.copyFile(sourceFile, targetFile);
                } catch (IOException e) {
                    log.error("Error copying file", e);
                }
            }
        });
    }

    private void saveContentAsFile(String targetPath, String content) {
        try {
            FileOperations.saveFile(targetPath, content);
        } catch (IOException e) {
            log.error("Error saving file {}", targetPath, e);
        }
    }

    private String compileMustacheTemplate(String templateFile, Map<String, Object> templateContextMap) {
        MustacheFactory mustacheFactory = new DefaultMustacheFactory();
        Mustache mustache = mustacheFactory.compile(templateFile);
        StringWriter stringWriter = new StringWriter();
        String content = null;
        try {
            mustache.execute(stringWriter, templateContextMap).flush();
            content = stringWriter.toString();
            log.debug("Rendered template: {}", content);
        } catch (IOException e) {
            log.error("Error executing template", e);
        }
        return content;
    }

    /**
     * Get all files from the specified source folder
     *
     * @param sourceFolder the folder in which to search for files
     * @return list of files in the specified folder
     */
    private List<File> listAllFiles(String sourceFolder) {
        List<Path> paths = new ArrayList<>();
        try {
            paths = Files.walk(Paths.get(sourceFolder)).filter(Files::isRegularFile).collect(Collectors.toList());
            paths.forEach(f -> log.debug("paths: {}", f));
        } catch (IOException e) {
            log.error("Error reading template folder", e);
        }
        List<File> files = new ArrayList<>();
        paths.forEach(path -> files.add(path.toFile()));
        return files;
    }

    public String basePackageToFolder(String basePackage) {
        return basePackage.replace(".", "/");
    }

}
