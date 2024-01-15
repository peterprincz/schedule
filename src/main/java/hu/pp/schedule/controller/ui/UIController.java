package hu.pp.schedule.controller.ui;

import hu.pp.schedule.enums.BusStation;
import hu.pp.schedule.service.DataRefreshJob;
import hu.pp.schedule.service.RouteService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class UIController {

    private static final Logger LOG = LoggerFactory.getLogger(UIController.class);

    private RouteService routeService;
    private DataRefreshJob refreshJob;

    @RequestMapping("/index")
    public String hello(Model model) {
        model.addAttribute("routesTo",
                routeService.listRoutes(List.of(BusStation.ALTANYI_SZOLOK, BusStation.DEAKVARI_FOUT),
                BusStation.AUTOBUSZ_ALLOMAS)
        );
        model.addAttribute("routesFrom",
                routeService.listRoutes(BusStation.AUTOBUSZ_ALLOMAS,
                List.of(BusStation.ALTANYI_SZOLOK, BusStation.DEAKVARI_FOUT))
        );
        model.addAttribute("cacheTime", refreshJob.getNextCacheEvictionTime());
        return "index";
    }

    @RequestMapping(value = "/login")
    public String login() {
        LOG.info("login get called");
        return "login";
    }

}
