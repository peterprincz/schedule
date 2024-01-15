package hu.pp.schedule.service;

import hu.pp.schedule.entity.Route;
import hu.pp.schedule.enums.BusStation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

@SpringBootTest()
public class BusRouteScrapingTrainRouteResponseServiceTests {

    @Autowired
    BusRouteScrapingService scrapingService;

    @Test
    void busScrapeTest() {
        List<Route> routes = scrapingService.getRoutes(new Date(), BusStation.ALTANYI_SZOLOK, BusStation.AUTOBUSZ_ALLOMAS);
        Assert.notEmpty(routes, "should have routes");
    }

}
