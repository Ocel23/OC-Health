package ocel23.me.ochealth.models;

public class Settings {

    private String defaultSectionOnStart;

    private boolean collectStatisticData;

    private String collectStatisticsDataInterval;

    private boolean showNotificationOnWarningValues;

    private String language;

    private boolean enableLogging;


    public String getDefaultSectionOnStart() {
        return defaultSectionOnStart;
    }

    public void setDefaultSectionOnStart(String defaultSectionOnStart) {
        this.defaultSectionOnStart = defaultSectionOnStart;
    }

    public boolean isCollectStatisticData() {
        return collectStatisticData;
    }

    public void setCollectStatisticData(boolean collectStatisticData) {
        this.collectStatisticData = collectStatisticData;
    }

    public String getCollectStatisticsDataInterval() {
        return collectStatisticsDataInterval;
    }

    public void setCollectStatisticsDataInterval(String collectStatisticsDataInterval) {
        this.collectStatisticsDataInterval = collectStatisticsDataInterval;
    }

    public boolean isShowNotificationOnWarningValues() {
        return showNotificationOnWarningValues;
    }

    public void setShowNotificationOnWarningValues(boolean showNotificationOnWarningValues) {
        this.showNotificationOnWarningValues = showNotificationOnWarningValues;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isEnableLogging() {
        return enableLogging;
    }

    public void setEnableLogging(boolean enableLogging) {
        this.enableLogging = enableLogging;
    }
}
