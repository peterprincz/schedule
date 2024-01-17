package hu.pp.schedule.service;

import hu.pp.schedule.model.Route;
import hu.pp.schedule.enums.BusStation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest()
public class BusCollectorServiceTests {

    @Autowired
    private BusCollectorService scrapingService;

    @Autowired
    private ZonedTimeService timeService;

    @Test
    void busScrapeTest() {
        LocalDateTime now = timeService.getTime();
        List<Route> routes = scrapingService.getRoutes(now, BusStation.ALTANYI_SZOLOK, BusStation.AUTOBUSZ_ALLOMAS);
        Assert.notEmpty(routes, "should have routes");
    }

}
