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

    public static boolean isExtension(String filename, String extension) {
        String fileExtension = FilenameUtils.getExtension(filename);
        return null != extension && !extension.isEmpty() && extension.toLowerCase().equals(fileExtension.toLowerCase());
    }

    public static String removeFileExtension(String filename) {
        return FilenameUtils.removeExtension(filename);
    }

    public static void fileCopy(String source, String target) throws IOException {
        File currentFile = new File(source);
        File backupFileName = new File(target);
        FileUtils.copyFile(currentFile, backupFileName);
    }

    public static void saveFile(String file, String string) throws IOException {
        FileUtils.writeStringToFile(new File(file), string, "UTF-8");
    }

}
