package hu.pp.schedule.service;

import hu.pp.schedule.enums.BusStation;
import hu.pp.schedule.enums.TrainStation;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class DataRefreshJob {

    private static final long HALF_HOUR =  60 * 30 * 1000;

    @Getter
    private Date nextCacheEvictionTime;

    private final RouteService routeService;

    public DataRefreshJob(RouteService routeService) {
        this.routeService = routeService;
    }

    @Scheduled(fixedRate = HALF_HOUR)
    public void refreshData() {
        routeService.evictCache();
        routeService.listRoutes(List.of(BusStation.ALTANYI_SZOLOK, BusStation.DEAKVARI_FOUT), BusStation.AUTOBUSZ_ALLOMAS);
        routeService.listRoutes(BusStation.AUTOBUSZ_ALLOMAS, List.of(BusStation.ALTANYI_SZOLOK, BusStation.DEAKVARI_FOUT));
        routeService.listRoutes(TrainStation.NYUGATI, TrainStation.VAC);
        routeService.listRoutes(TrainStation.VAC, TrainStation.NYUGATI);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MILLISECOND, (int) HALF_HOUR);
        nextCacheEvictionTime = calendar.getTime();
    }

}