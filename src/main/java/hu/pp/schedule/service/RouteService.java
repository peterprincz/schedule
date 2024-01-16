package hu.pp.schedule.service;

import hu.pp.schedule.entity.Route;
import hu.pp.schedule.enums.BusStation;
import hu.pp.schedule.enums.TrainStation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class RouteService {

    private static final Logger LOG = LoggerFactory.getLogger(RouteService.class);

    private BusRouteScrapingService busScrapingService;
    private TrainRouteScrapingService trainScrapingService;

    public RouteService(BusRouteScrapingService busScrapingService, TrainRouteScrapingService trainScrapingService) {
        this.busScrapingService = busScrapingService;
        this.trainScrapingService = trainScrapingService;
    }

    @Cacheable(value = "externalData")
    public List<Route> listRoutes(Date day, List<BusStation> fromList, BusStation to) {
        return fromList.stream().map(from -> busScrapingService.getRoutes(day, from, to))
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(Route::getDepartTime))
                .toList();
    }

    @Cacheable(value = "externalData")
    public List<Route> listRoutes(Date day, BusStation from, List<BusStation> toList) {
        return toList.stream().map(to -> busScrapingService.getRoutes(day, from, to))
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(Route::getDepartTime))
                .toList();
    }

    @Cacheable(value = "externalData")
    public List<Route> listRoutes(Date day, TrainStation from, TrainStation to) {
        return trainScrapingService.getRoutes(day, from, to)
                .stream()
                .sorted(Comparator.comparing(Route::getDepartTime))
                .toList();
    }

    @CacheEvict(value = "externalData", allEntries = true)
    public void evictCache() {
        LOG.info("Evicting cache");
    }
}
