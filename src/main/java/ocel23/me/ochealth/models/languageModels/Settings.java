package ocel23.me.ochealth.models.languageModels;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Settings {

    private String title;
    private String defaultSectionOnStartApp;
    private String collectStatisticsData;
    private String collectStatisticDataInterval;
    private String showNotificationsOnWarningValues;
    private String exportData;
    private String language;
    private String enableLogging;
    private String collectDataInfo;
    private String reportBugInfo;

}
