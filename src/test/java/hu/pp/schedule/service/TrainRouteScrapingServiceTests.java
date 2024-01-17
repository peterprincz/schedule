package hu.pp.schedule.service;

import hu.pp.schedule.entity.Route;
import hu.pp.schedule.enums.TrainStation;
import hu.pp.schedule.util.TimeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest()
public class TrainRouteScrapingServiceTests {

    @Autowired
    TrainRouteScrapingService scrapingService;

    @Autowired
    private TimeService timeService;

    @Test
    void busScrapeTest() {
        LocalDateTime now = timeService.getTime();
        List<Route> routes = scrapingService.getRoutes(now, TrainStation.VAC, TrainStation.NYUGATI);
        Assert.notEmpty(routes, "should have routes");
    }

}
