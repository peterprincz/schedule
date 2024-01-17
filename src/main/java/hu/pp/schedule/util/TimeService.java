package hu.pp.schedule.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class TimeService {

    @Value( "${application.timezone}" )
    private String timeZone;

    public LocalDateTime timeStringToDate(LocalDateTime day, String time) {
        return day.withHour(Integer.parseInt(time.substring(0, 2)))
                .withMinute(Integer.parseInt(time.substring(3, 5)))
                .withSecond(0);
    }

    public LocalDateTime getTime(){
        ZoneId zoneId = getZoneId();
        return LocalDateTime.now(zoneId);
    }

    public ZoneId getZoneId(){
        return ZoneId.of(timeZone);
    }

}
