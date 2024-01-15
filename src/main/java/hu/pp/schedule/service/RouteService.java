package hu.pp.schedule.service;

import hu.pp.schedule.entity.Route;
import hu.pp.schedule.enums.BusStation;
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

    private BusRouteScrapingService scrapingService;

    public RouteService(BusRouteScrapingService scrapingService) {
        this.scrapingService = scrapingService;
    }

    @Cacheable(value = "externalData")
    public List<Route> listRoutes(List<BusStation> fromList, BusStation to) {
        return fromList.stream().map(from -> scrapingService.getRoutes(new Date(), from, to))
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(Route::getArrival))
                .toList();
    }

    @Cacheable(value = "externalData")
    public List<Route> listRoutes(BusStation from, List<BusStation> toList) {
        return toList.stream().map(to -> scrapingService.getRoutes(new Date(), from, to))
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(Route::getArrival))
                .toList();
    }

    @CacheEvict(value = "externalData", allEntries = true)
    public void evictCache() {
        LOG.info("Evicting cache");
    }
}
