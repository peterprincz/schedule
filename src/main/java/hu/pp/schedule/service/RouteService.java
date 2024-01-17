package hu.pp.schedule.service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

import hu.pp.schedule.model.Route;
import hu.pp.schedule.enums.BusStation;
import hu.pp.schedule.enums.TrainStation;

@Service
@AllArgsConstructor
public class RouteService {

    private static final Logger LOG = LoggerFactory.getLogger(RouteService.class);

    private final CollectorService<BusStation> busRouteCollectorService;
    private final CollectorService<TrainStation> trainRouteCollectorService;

    @Cacheable(value = "externalData", key = "{#fromList, #to}")
    public List<Route> listRoutes(LocalDateTime day, List<BusStation> fromList, BusStation to) {
        return fromList.stream().map(from -> busRouteCollectorService.getRoutes(day, from, to))
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(Route::getDepartTime))
                .toList();
    }

    @Cacheable(value = "externalData", key = "{#from, #toList}")
    public List<Route> listRoutes(LocalDateTime day, BusStation from, List<BusStation> toList) {
        return toList.stream().map(to -> busRouteCollectorService.getRoutes(day, from, to))
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(Route::getDepartTime))
                .toList();
    }

    @Cacheable(value = "externalData", key = "{#from, #to}")
    public List<Route> listRoutes(LocalDateTime day, TrainStation from, TrainStation to) {
        return trainRouteCollectorService.getRoutes(day, from, to)
                .stream()
                .sorted(Comparator.comparing(Route::getDepartTime))
                .toList();
    }

    @CacheEvict(value = "externalData", allEntries = true)
    public void evictCache() {
        LOG.info("Evicting cache");
    }
}
