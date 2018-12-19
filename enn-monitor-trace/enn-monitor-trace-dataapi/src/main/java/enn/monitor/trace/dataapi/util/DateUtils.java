package enn.monitor.trace.dataapi.util;

import enn.monitor.framework.common.time.EnnDatetimeUtil;
import enn.monitor.framework.common.time.EnnTimezoneUtil;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by weize on 18-1-7.
 */
public class DateUtils {
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

    public static List<LocalDate> getDatesBetween(
            LocalDate startDate, LocalDate endDate) {

        long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        return IntStream.iterate(0, i -> i + 1)
                .limit(numOfDaysBetween)
                .mapToObj(i -> startDate.plusDays(i))
                .collect(Collectors.toList());
    }
}
