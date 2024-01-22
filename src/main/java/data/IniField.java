package data;

public enum IniField {
    BACKUP_INTERVAL("backup_interval_in_seconds"),
    BACKUP_LOCATION("backup_location"),

    SAVE_FILE_LOCATION("save_location");

    public final String fieldName;

    IniField(String fieldName) {
        this.fieldName = fieldName;
    }
}
