package enn.monitor.framework.common.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// 单位是毫秒
public class EnnDatetimeUtil {
	private static final Logger logger = LogManager.getLogger();
    
    // yy-mm-dd hh:mm:ss
    public static int getMillisByTime(String duration) {
        int millis = 0;

        String durationArray[] = duration.split(":");

        millis = Integer.parseInt(durationArray[0]);
        millis = millis * 60 + Integer.parseInt(durationArray[1]);
        millis = millis * 60 + Integer.parseInt(durationArray[2]);

        millis = millis * 1000;

        return millis;
    }

    public static long getMillisSecondsWithOneDay() {
        return 1 * 24 * 60 * 60 * 1000;
    }

    public static long getMillisSecondsWithOneHour() {
        return 1 * 60 * 60 * 1000;
    }
    
    public static int getDayOfWeek(long datetime, TimeZone timeZone) {
        Calendar calendar = Calendar.getInstance();
        
        calendar.setTimeZone(timeZone);
        calendar.setTimeInMillis(datetime);
        
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static String getCurrentDateTime(TimeZone timeZone) {
        return getCurrentDateTime("-", timeZone);
    }

    public static String getCurrentDateTime(String separator, TimeZone timeZone) {
        return internalGetCurrentDateTime(getDateTimeFormatString(separator), timeZone);
    }
    
    public static String getCurrentYear(TimeZone timeZone) {
    	return internalGetCurrentDateTime(getYearFormatString(), timeZone);
    }
    
    public static String getCurrentMonth(TimeZone timeZone) {
    	return internalGetCurrentDateTime(getMonthFormatString(), timeZone);
    }
    
    public static String getCurrentDay(TimeZone timeZone) {
    	return internalGetCurrentDateTime(getDayFormatString(), timeZone);
    }

    public static String getCurrentDate(TimeZone timeZone) {
        return getCurrentDate("-", timeZone);
    }
    
    public static long getCurrentDateMills(TimeZone timeZone) {
    	return convertStringToLongWithDate(getCurrentDate(timeZone), timeZone);
    }

    public static String getCurrentDate(String separator, TimeZone timeZone) {
        return internalGetCurrentDateTime(getDateFormatString(separator), timeZone);
    }
    
    public static long convertStringToLongWithTime(String timeString, TimeZone timeZone) {
        timeString = "1970-01-02 " + timeString;
        return convertStringToLongWithDateTime(timeString, "-", timeZone) % getMillisSecondsWithOneDay();
    }
    
    public static long convertStringToLongWithUsedFormat(String timeString, String format, TimeZone timeZone) {
    	return internalConvertStringToLongWithDateTime(timeString, format, timeZone) % getMillisSecondsWithOneDay();
    }

    public static long convertStringToLongWithDateTime(String dateString, String separator, TimeZone timeZone) {
        return internalConvertStringToLongWithDateTime(dateString, getDateTimeFormatString(separator), timeZone);
    }

    public static long convertStringToLongWithDateTime(String dateString, TimeZone timeZone) {
        return convertStringToLongWithDateTime(dateString, "-", timeZone);
    }

    public static long convertStringToLongWithDate(String dateString, String separator, TimeZone timeZone) {
        return internalConvertStringToLongWithDateTime(dateString, getDateFormatString(separator), timeZone);
    }

    public static long convertStringToLongWithDate(String dateString, TimeZone timeZone) {
        return convertStringToLongWithDate(dateString, "-", timeZone);
    }

    public static String convertLongToStringWithDateTime(long dateTimeMillis, TimeZone timeZone) {
        return convertLongToStringWithDateTime(dateTimeMillis, "-", timeZone);
    }

    public static String convertLongToStringWithDateTime(long dateTimeMillis, String separator, TimeZone timeZone) {
        return internalConverLongToStringWithDateTime(dateTimeMillis, getDateTimeFormatString(separator), timeZone);
    }
    
    public static String convertLongToStringWithDate(long dateTimeMillis, TimeZone timeZone) {
        return convertLongToStringWithDate(dateTimeMillis, "-", timeZone);
    }

    public static String convertLongToStringWithDate(long dateTimeMillis, String separator, TimeZone timeZone) {
        return internalConverLongToStringWithDateTime(dateTimeMillis, getDateFormatString(separator), timeZone);
    }
    
    public static String convertLongToStringWithTime(long timeMills, TimeZone timeZone) {
        String dataString = internalConverLongToStringWithDateTime(timeMills, getTimeFormatString(), timeZone);
        return dataString;
    }
    
    public static String convertLongToStringWithUsedFormat(long timeMills, String format, TimeZone timeZone) {
        String dataString = internalConverLongToStringWithDateTime(timeMills, format, timeZone);
        return dataString;
    }

    public static List<String> getDatesBetween(long ts1, long ts2) {
        DateTimeFormatter sdf = DateTimeFormatter.ISO_DATE;
        LocalDate date1 = Instant.ofEpochSecond(ts1).atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate date2 = Instant.ofEpochSecond(ts2).atZone(ZoneId.systemDefault()).toLocalDate();
        List<LocalDate> dates = getDatesBetween(date1, date2); // date1 inclusive; date2 exclusive;
        List<String> res = new ArrayList<>();
        dates.forEach(date -> {
            res.add(date.format(sdf));
        });
        res.add(date2.format(sdf));
        return res;
    }

    private static List<LocalDate> getDatesBetween(
            LocalDate startDate, LocalDate endDate) {
        long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        return IntStream.iterate(0, i -> i + 1)
                .limit(numOfDaysBetween)
                .mapToObj(i -> startDate.plusDays(i))
                .collect(Collectors.toList());
    }
    
    private static String getYearFormatString() {
        return "yyyy";
    }
    
    private static String getMonthFormatString() {
        return "MM";
    }
    
    private static String getDayFormatString() {
        return "dd";
    }

    private static String getDateFormatString(String separator) {
        return "yyyy" + separator + "MM" + separator + "dd";
    }

    private static String getDateTimeFormatString(String separator) {
        return "yyyy" + separator + "MM" + separator + "dd" + " HH:mm:ss";
    }
    
    private static String getTimeFormatString() {
        return "HH:mm:ss";
    }

    private static String internalGetCurrentDateTime(String dateFormat, TimeZone timeZone) {
        SimpleDateFormat simpleDf = new SimpleDateFormat(dateFormat);
        simpleDf.setTimeZone(timeZone);
        return simpleDf.format(new Date());
    }

    // 传进去的字符串代表的是哪个时区
    private static long internalConvertStringToLongWithDateTime(String dateString, String dateFormat, TimeZone timeZone) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = null;

        try {
            sdf.setTimeZone(timeZone);
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
            return 0;
        }

        return date.getTime();
    }

    // timeZone表示的是返回的是哪个时区的表达式
    private static String internalConverLongToStringWithDateTime(long dateTimeMillis, String dateFormat, TimeZone timeZone) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

        sdf.setTimeZone(timeZone);
        Date date = new Date(dateTimeMillis);

        return sdf.format(date);
    }
    
    public static void main(String []args) throws Exception {
    	System.out.println(EnnDatetimeUtil.getCurrentYear(EnnTimezoneUtil.getChinaTimeZone()));
    	System.out.println(EnnDatetimeUtil.getCurrentMonth(EnnTimezoneUtil.getChinaTimeZone()));
    	System.out.println(EnnDatetimeUtil.getCurrentDay(EnnTimezoneUtil.getChinaTimeZone()));
    	System.out.println(internalGetCurrentDateTime("dd", EnnTimezoneUtil.getChinaTimeZone()));
    }
}
