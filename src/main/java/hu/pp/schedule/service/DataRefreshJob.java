package hu.pp.schedule.service;

import hu.pp.schedule.enums.BusStation;
import hu.pp.schedule.enums.TrainStation;
import hu.pp.schedule.util.TimeUtils;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Component
public class DataRefreshJob {

    private static final long HALF_HOUR =  60 * 30 * 1000;

    @Getter
    private Date lastCacheEvictionTime;

    private final RouteService routeService;

    public DataRefreshJob(RouteService routeService) {
        this.routeService = routeService;
    }

    @Scheduled(fixedRate = HALF_HOUR)
    public void refreshData() {
        Date now = TimeUtils.getCurrentETCTime();
        routeService.evictCache();
        routeService.listRoutes(now, List.of(BusStation.ALTANYI_SZOLOK, BusStation.DEAKVARI_FOUT), BusStation.AUTOBUSZ_ALLOMAS);
        routeService.listRoutes(now, BusStation.AUTOBUSZ_ALLOMAS, List.of(BusStation.ALTANYI_SZOLOK, BusStation.DEAKVARI_FOUT));
        routeService.listRoutes(now, TrainStation.NYUGATI, TrainStation.VAC);
        routeService.listRoutes(now, TrainStation.VAC, TrainStation.NYUGATI);
        lastCacheEvictionTime = now;
    }

}