package hu.pp.schedule.service;

import java.time.LocalDateTime;
import java.time.ZoneId;

public interface TimeService {

    LocalDateTime timeStringToDate(LocalDateTime day, String time);
    LocalDateTime getTime();
    ZoneId getZoneId();

}
