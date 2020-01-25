package helper;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;

/**
 * @author Ruslan Spivak
 */
@Slf4j
public class FileOperations {

    public static boolean isMatchExtension(String filename, String extension) {
        String fileExtension = FilenameUtils.getExtension(filename);
        return null != extension && !extension.isEmpty() && extension.toLowerCase().equals(fileExtension.toLowerCase());
    }

    public static String removeFileExtension(String filename) {
        return FilenameUtils.removeExtension(filename);
    }

    public static void copyFile(String source, String target) throws IOException {
        FileUtils.copyFile(new File(source), new File(target));
    }

    public static void saveFile(String file, String content) throws IOException {
        FileUtils.writeStringToFile(new File(file), content, "UTF-8");
    }

}
