package fileBackup;

import org.apache.commons.configuration2.INIConfiguration;
import org.apache.commons.configuration2.SubnodeConfiguration;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static data.IniField.*;
import static data.IniSection.PATHS_SECTION;
import static data.IniSection.TIMERS_SECTION;

public class IniProcessor {

    public static INIConfiguration readIniFile(String path) {

        File fileToParse = new File(path);

        INIConfiguration iniConfiguration = new INIConfiguration();
        try (FileReader fileReader = new FileReader(fileToParse)) {
            iniConfiguration.read(fileReader);
        } catch (ConfigurationException | IOException e) {
            throw new RuntimeException(e);
        }

        return iniConfiguration;
    }

    public static void setProperties(INIConfiguration iniConfiguration) {

        SubnodeConfiguration pathsSection = iniConfiguration.getSection(PATHS_SECTION.sectionName);
        SubnodeConfiguration timersSection = iniConfiguration.getSection(TIMERS_SECTION.sectionName);

        System.setProperty(SAVE_FILE_LOCATION.fieldName, pathsSection.getProperty(SAVE_FILE_LOCATION.fieldName).toString());
        System.setProperty(BACKUP_LOCATION.fieldName, pathsSection.getProperty(BACKUP_LOCATION.fieldName).toString());
        System.setProperty(BACKUP_INTERVAL.fieldName, timersSection.getProperty(BACKUP_INTERVAL.fieldName).toString());
    }
}
