package data;

public enum IniSection {

    PATHS_SECTION("paths"),
    TIMERS_SECTION("timers");

    public final String sectionName;

    IniSection(String sectionName) {
        this.sectionName = sectionName;
    }
}
