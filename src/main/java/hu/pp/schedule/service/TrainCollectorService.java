package hu.pp.schedule.service;

import hu.pp.schedule.model.Route;
import hu.pp.schedule.enums.TrainStation;
import hu.pp.schedule.enums.TransferType;
import hu.pp.schedule.model.train.TrainRouteResponse;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;


@Service
@AllArgsConstructor
public class TrainCollectorService implements CollectorService<TrainStation> {

    private TimeService timeService;

    private static final Logger LOG = LoggerFactory.getLogger(BusCollectorService.class);

    private static final String URL_PATTERN = "https://apiv2.oroszi.net/elvira?from=${FROM}&to=${TO}";

    public List<Route> getRoutes(LocalDateTime day, TrainStation from, TrainStation to) {
        LOG.info("Getting train routes for day: {}, from: {}, to: {}", day, from, to);
        RestTemplate restTemplate = new RestTemplate();
        String url = URL_PATTERN.replace("${FROM}", from.getValue()).replace("${TO}", to.getValue());
        ResponseEntity<TrainRouteResponse> response = restTemplate.getForEntity(url, TrainRouteResponse.class);
        if (response.getStatusCode().isError() || response.getBody() == null || !response.hasBody()) {
            throw new RuntimeException("Error while fetching train routes. status: " + response.getStatusCode());
        }
        List<Route> result = response.getBody().getTimetable()
                .stream()
                .filter(train -> !StringUtils.hasText(train.getChange()))
                .map(train -> new Route(TransferType.TRAIN,
                        train.getDetails().get(0).getFrom(),
                        train.getDetails().get(1).getFrom(),
                        timeService.timeStringToDate(day, train.getStartTime()),
                        timeService.timeStringToDate(day, train.getDestinationTime()),
                        train.getDistance()
                ))
                .toList();
        LOG.info("Successfully received routes for day: {}, from: {}, to: {} ({}) ", day, from, to, result.size());
        return result;
    }

}
