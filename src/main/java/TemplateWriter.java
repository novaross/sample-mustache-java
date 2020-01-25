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
import java.util.stream.Collectors;

@Data
@Slf4j
public class TemplateWriter {

    private TemplateContext templateContext;
    private String templateFolder;
    private String outputFolder;

    public void process() {
        List<File> files = getTemplateFolderFiles(templateFolder);
        files.forEach(file -> {
            String sourceFile = file.getAbsolutePath();
            int indexOfTemplateFolder = sourceFile.indexOf(templateFolder);
            String targetFile = outputFolder + sourceFile.substring(indexOfTemplateFolder + templateFolder.length());
            if (FileOperations.isExtension(file.getName(), "mustache")) {
                // this is a template file, process and place in the new location
                targetFile = FileOperations.removeFileExtension(targetFile);
                log.debug("TemplateFile: {}, targetFile: {}", sourceFile, targetFile);
                compileAndWriteFile(sourceFile, targetFile, templateContext);
            } else {
                try {
                    FileOperations.fileCopy(sourceFile, targetFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void compileAndWriteFile(String sourceFile, String targetFile, TemplateContext templateContext) {
        log.debug("Template file: {}, Target file: {}", sourceFile, targetFile);
        String data = compileMustacheTemplate(sourceFile, templateContext);
        saveContentAsFile(data, targetFile);
    }

    private void saveContentAsFile(String content, String targetPath) {
        try {
            FileOperations.saveFile(targetPath, content);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Error saving file {}", targetPath, e);
        }
    }

    private String compileMustacheTemplate(String templateFileName, TemplateContext templateContext) {
        MustacheFactory mustacheFactory = new DefaultMustacheFactory();
        Mustache mustache = mustacheFactory.compile(templateFileName);
        StringWriter stringWriter = new StringWriter();
        String content = null;
        try {
            mustache.execute(stringWriter, templateContext).flush();
            content = stringWriter.toString();
            log.debug("Rendered template: {}", content);
        } catch (IOException e) {
            log.error("Error executing template", e);
        }
        return content;
    }

    private List<File> getTemplateFolderFiles(String templatePath) {
        List<Path> paths = new ArrayList<>();
        try {
            paths = Files.walk(Paths.get(templatePath)).filter(Files::isRegularFile).collect(Collectors.toList());
            paths.forEach(f -> log.debug("paths: {}", f));
        } catch (IOException e) {
            log.error("Error reading template folder", e);
        }
        List<File> files = new ArrayList<>();
        paths.forEach(path -> files.add(path.toFile()));
        return files;
    }

}
