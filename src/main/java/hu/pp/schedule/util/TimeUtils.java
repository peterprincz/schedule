package hu.pp.schedule.util;

import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

    public static Date timeStringToDate(Date day, String time) {
        Calendar date = Calendar.getInstance();
        date.setTime(day);
        date.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time.substring(0, 2)));
        date.set(Calendar.MINUTE, Integer.parseInt(time.substring(3, 5)));
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        return date.getTime();
    }

}
