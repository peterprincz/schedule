package hu.pp.schedule.service;

import hu.pp.schedule.entity.Route;
import hu.pp.schedule.enums.TrainStation;
import hu.pp.schedule.enums.TransferType;
import hu.pp.schedule.model.train.TrainRouteResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static hu.pp.schedule.util.TimeUtils.timeStringToDate;

@Service
public class TrainRouteScrapingService {

    private static final Logger LOG = LoggerFactory.getLogger(BusRouteScrapingService.class);

    private static final String URL_PATTERN = "https://apiv2.oroszi.net/elvira?from=${FROM}&to=${TO}";

    public List<Route> getRoutes(Date day, TrainStation from, TrainStation to) {
        LOG.info("Getting train routes for day: {}, from: {}, to: {}", day, from, to);
        RestTemplate restTemplate = new RestTemplate();
        String url = URL_PATTERN.replace("${FROM}", from.getValue()).replace("${TO}", to.getValue());
        ResponseEntity<TrainRouteResponse> response = restTemplate.getForEntity(url, TrainRouteResponse.class);
        if (response.getStatusCode().isError() || response.getBody() == null || !response.hasBody()) {
            throw new RuntimeException("Error while fetching train routes. status: " + response.getStatusCode());
        }
        List<Route> result = response.getBody().getTimetable()
                .stream()
                .map(train -> new Route(TransferType.TRAIN,
                        train.getDetails().get(0).getFrom(),
                        train.getDetails().get(1).getFrom(),
                        timeStringToDate(day, train.getStartTime()),
                        timeStringToDate(day, train.getDestinationTime())
                ))
                .toList();
        LOG.info("Successfully received routes for day: {}, from: {}, to: {} ({}) ", day, from, to, result.size());
        return result;
    }

}
