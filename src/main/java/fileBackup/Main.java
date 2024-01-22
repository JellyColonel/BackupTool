package fileBackup;

import org.apache.commons.configuration2.INIConfiguration;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static data.IniField.*;
import static fileBackup.FileProcessor.createBackup;
import static fileBackup.IniProcessor.readIniFile;
import static fileBackup.IniProcessor.setProperties;
import static java.lang.Integer.parseInt;
import static java.lang.System.getProperty;
import static java.util.concurrent.TimeUnit.*;

public class Main {

    public static void main(String[] args) {

        // Setup Properties
        INIConfiguration iniConfiguration = readIniFile("settings/settings.ini");
        setProperties(iniConfiguration);

        // Debug
        System.out.println(getProperty(SAVE_FILE_LOCATION.fieldName) + "\\");
        System.out.println(getProperty(BACKUP_LOCATION.fieldName) + "\\");

        // Execute
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(3);

        // Schedule a task to run every N minutes with no initial delay.
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                createBackup();
            }
        }, 0L, // Delay
                parseInt(getProperty(BACKUP_INTERVAL.fieldName)), // Polling interval
                SECONDS); // Time Unit
    }
}

