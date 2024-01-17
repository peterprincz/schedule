package hu.pp.schedule.util;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class TimeUtils {

    public static LocalDateTime timeStringToDate(LocalDateTime day, String time) {
        return day.withHour(Integer.parseInt(time.substring(0, 2)))
                .withMinute(Integer.parseInt(time.substring(3, 5)))
                .withSecond(0);
    }

    public static LocalDateTime getCurrentETCTime(){
        ZoneId zoneId = getZoneId();
        return LocalDateTime.now(zoneId);
    }

    public static ZoneId getZoneId(){
        return ZoneId.of("Europe/Budapest");
    }

}
