package enn.monitor.framework.common.time;

import java.util.TimeZone;

public class EnnTimezoneUtil {
	
    public static final String GMTTIMEZONE_STRING = "Etc/GMT";
    public static final String CHINATIMEZONE_STRING = "Asia/Shanghai";

    public static TimeZone getCurrentTimeZone() {
        return TimeZone.getTimeZone(System.getProperty("user.timezone"));
    }

    public static TimeZone getGMTTimeZone() {
        return getTimeZone(GMTTIMEZONE_STRING);
    }

    public static TimeZone getChinaTimeZone() {
        return getTimeZone(CHINATIMEZONE_STRING);

    }

    public static TimeZone getTimeZone(String timezoneString) {
        return TimeZone.getTimeZone(timezoneString);
    }

    public static long getOffset(TimeZone timezone, long date) {
        return timezone.getOffset(date);
    }

}
