package hu.pp.schedule.controller.ui;

import hu.pp.schedule.enums.BusStation;
import hu.pp.schedule.enums.TrainStation;
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


    @RequestMapping("/")
    public String hello(Model model) {
        model.addAttribute("busRoutesToCity",
                routeService.listRoutes(List.of(BusStation.ALTANYI_SZOLOK, BusStation.DEAKVARI_FOUT),
                BusStation.AUTOBUSZ_ALLOMAS)
        );
        model.addAttribute("busRoutesToCity",
                routeService.listRoutes(BusStation.AUTOBUSZ_ALLOMAS,
                List.of(BusStation.ALTANYI_SZOLOK, BusStation.DEAKVARI_FOUT))
        );
        model.addAttribute("trainRoutesToBudapest",
                routeService.listRoutes(TrainStation.VAC, TrainStation.NYUGATI)
        );
        model.addAttribute("trainRoutesToVac",
                routeService.listRoutes(TrainStation.NYUGATI, TrainStation.VAC)
        );

        model.addAttribute("nextCacheTime", refreshJob.getNextCacheEvictionTime());
        return "index";
    }
}
