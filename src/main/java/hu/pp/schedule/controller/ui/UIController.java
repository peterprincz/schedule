package hu.pp.schedule.controller.ui;

import hu.pp.schedule.enums.BusStation;
import hu.pp.schedule.enums.TrainStation;
import hu.pp.schedule.service.DataRefreshJob;
import hu.pp.schedule.service.RouteService;
import hu.pp.schedule.util.TimeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@AllArgsConstructor
public class UIController {

    private RouteService routeService;
    private DataRefreshJob refreshJob;
    private TimeService timeService;

    @RequestMapping("/")
    public String index(Model model) {
        LocalDateTime now = timeService.getTime();
        model.addAttribute("systemTime", now);
        model.addAttribute("lastCacheTime", refreshJob.getLastCacheEvictionTime());
        model.addAttribute("busRoutesToCity",
                routeService.listRoutes(now,
                        List.of(BusStation.ALTANYI_SZOLOK, BusStation.DEAKVARI_FOUT),
                        BusStation.AUTOBUSZ_ALLOMAS
                )
        );
        model.addAttribute("busRoutesFromCity",
                routeService.listRoutes(now,
                        BusStation.AUTOBUSZ_ALLOMAS,
                        List.of(BusStation.ALTANYI_SZOLOK, BusStation.DEAKVARI_FOUT)
                )
        );
        model.addAttribute("trainRoutesToBudapest",
                routeService.listRoutes(now, TrainStation.VAC, TrainStation.NYUGATI)
        );
        model.addAttribute("trainRoutesFromBudapest",
                routeService.listRoutes(now, TrainStation.NYUGATI, TrainStation.VAC)
        );
        return "index";
    }
}
