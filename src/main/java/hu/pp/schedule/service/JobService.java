package hu.pp.schedule.service;

import hu.pp.schedule.enums.BusStation;
import hu.pp.schedule.enums.TrainStation;
import lombok.Getter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class JobService {

    private static final long HALF_HOUR = 60 * 30 * 1000;

    private final RouteService routeService;
    private final TimeService timeService;

    @Getter
    private LocalDateTime lastCacheEvictionTime;

    public JobService(RouteService routeService, ZonedTimeService timeService) {
        this.routeService = routeService;
        this.timeService = timeService;
    }

    @Scheduled(fixedRate = HALF_HOUR)
    public void refreshData() {
        LocalDateTime now = timeService.getTime();
        routeService.evictCache();
        routeService.listRoutes(now, List.of(BusStation.ALTANYI_SZOLOK, BusStation.DEAKVARI_FOUT), BusStation.AUTOBUSZ_ALLOMAS);
        routeService.listRoutes(now, BusStation.AUTOBUSZ_ALLOMAS, List.of(BusStation.ALTANYI_SZOLOK, BusStation.DEAKVARI_FOUT));
        routeService.listRoutes(now, TrainStation.NYUGATI, TrainStation.VAC);
        routeService.listRoutes(now, TrainStation.VAC, TrainStation.NYUGATI);
        lastCacheEvictionTime = now;
    }

}