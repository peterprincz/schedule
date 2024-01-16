package hu.pp.schedule.service;

import hu.pp.schedule.entity.Route;
import hu.pp.schedule.enums.BusStation;
import hu.pp.schedule.util.TimeUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

@SpringBootTest()
public class BusRouteScrapingServiceTests {

    @Autowired
    BusRouteScrapingService scrapingService;

    @Test
    void busScrapeTest() {
        Date now = TimeUtils.getCurrentETCTime();
        List<Route> routes = scrapingService.getRoutes(now, BusStation.ALTANYI_SZOLOK, BusStation.AUTOBUSZ_ALLOMAS);
        Assert.notEmpty(routes, "should have routes");
    }

}
