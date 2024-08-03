package ocel23.me.ochealth.models.languageModels;

public class Settings {

    private String defaultSectionOnStartApp;
    private String collectStatisticsData;
    private String collectStatisticDataInterval;
    private String showNotificationsOnWarningValues;
    private String exportData;
    private String language;
    private String enableLogging;
    private String collectDataInfo;
    private String reportBugInfo;

    public String getDefaultSectionOnStartApp() {
        return defaultSectionOnStartApp;
    }

    public void setDefaultSectionOnStartApp(String defaultSectionOnStartApp) {
        this.defaultSectionOnStartApp = defaultSectionOnStartApp;
    }

    public String getCollectStatisticsData() {
        return collectStatisticsData;
    }

    public void setCollectStatisticsData(String collectStatisticsData) {
        this.collectStatisticsData = collectStatisticsData;
    }

    public String getCollectStatisticDataInterval() {
        return collectStatisticDataInterval;
    }

    public void setCollectStatisticDataInterval(String collectStatisticDataInterval) {
        this.collectStatisticDataInterval = collectStatisticDataInterval;
    }

    public String getShowNotificationsOnWarningValues() {
        return showNotificationsOnWarningValues;
    }

    public void setShowNotificationsOnWarningValues(String showNotificationsOnWarningValues) {
        this.showNotificationsOnWarningValues = showNotificationsOnWarningValues;
    }

    public String getExportData() {
        return exportData;
    }

    public void setExportData(String exportData) {
        this.exportData = exportData;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getEnableLogging() {
        return enableLogging;
    }

    public void setEnableLogging(String enableLogging) {
        this.enableLogging = enableLogging;
    }

    public String getCollectDataInfo() {
        return collectDataInfo;
    }

    public void setCollectDataInfo(String collectDataInfo) {
        this.collectDataInfo = collectDataInfo;
    }

    public String getReportBugInfo() {
        return reportBugInfo;
    }

    public void setReportBugInfo(String reportBugInfo) {
        this.reportBugInfo = reportBugInfo;
    }
}
