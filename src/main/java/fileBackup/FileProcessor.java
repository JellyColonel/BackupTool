package fileBackup;

import data.IniField;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.FileFilterUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Optional;

import static data.IniField.BACKUP_LOCATION;
import static data.IniField.SAVE_FILE_LOCATION;
import static java.lang.System.getProperty;

public class FileProcessor {

    public static void createBackup() {
        File original = getLastUpdatedFile();
        String copiedFileName =  new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + original.getName();
        File copied = new File(getProperty(BACKUP_LOCATION.fieldName) + "\\" + copiedFileName);
        try {
        FileUtils.copyFile(original, copied);
        int debug = 0;
        }
        catch (IOException e) {
            System.err.println("Could not copy file");
        }

    }

    private static File getLastUpdatedFile() {
        File dir = new File(getProperty(SAVE_FILE_LOCATION.fieldName));
        if (dir.isDirectory()) {
            File[] dirFiles = dir.listFiles((FileFilter) FileFilterUtils.fileFileFilter());
            if (dirFiles != null && dirFiles.length > 0) {
                Arrays.sort(dirFiles, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
                return dirFiles[0];
            }
        }

        throw new RuntimeException("Could not parse newest file");
    }
}
