package ocel23.me.ochealth.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Settings {

    private String defaultSectionOnStart;
    private boolean collectStatisticData;
    private String collectStatisticsDataInterval;
    private boolean showNotificationOnWarningValues;
    private String language;
    private boolean enableLogging;

}
