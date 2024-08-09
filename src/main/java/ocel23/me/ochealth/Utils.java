package ocel23.me.ochealth;

import ocel23.me.ochealth.fileHandlers.ConfigHandler;

public class Utils {

    public static long getInterval(ConfigHandler configHandler) {

        String intervalValue = configHandler.getSettingsFromConfig().getCollectStatisticsDataInterval();

        long interval = 0;

        if (intervalValue.equalsIgnoreCase("1  minute")) {
            interval = 60000L;
        } else if (intervalValue.equalsIgnoreCase("5 minutes")) {
            interval = 3600000L;
        } else if (intervalValue.equalsIgnoreCase("15 minutes")) {
            interval = 10800000L;
        } else if (intervalValue.equalsIgnoreCase("30 minutes")) {
            interval = 21600000L;
        } else {
            interval = 1000L;
        }

        return interval;

    }
}
