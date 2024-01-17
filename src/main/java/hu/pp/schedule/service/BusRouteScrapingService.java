package hu.pp.schedule.service;

import hu.pp.schedule.entity.Route;
import hu.pp.schedule.enums.BusStation;
import hu.pp.schedule.enums.TransferType;
import hu.pp.schedule.model.bus.BusRouteRequest;
import hu.pp.schedule.model.bus.BusRouteResponse;
import hu.pp.schedule.util.TimeService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class BusRouteScrapingService {

    private TimeService timeService;

    private static final Logger LOG = LoggerFactory.getLogger(BusRouteScrapingService.class);

    private static final String URL = "https://menetrendek.hu/menetrend/newinterface/index.php";

    public List<Route> getRoutes(LocalDateTime day, BusStation from, BusStation to) {
        LOG.info("Getting bus routes for day: {}, from: {}, to: {}", day, from, to);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        BusRouteRequest requestBody = BusRouteRequest.create(day, from, to);
        HttpEntity<BusRouteRequest> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<BusRouteResponse> response = restTemplate.postForEntity(URL, requestEntity, BusRouteResponse.class);
        if (response.getStatusCode().isError() || response.getBody() == null || !response.hasBody()) {
            throw new RuntimeException("Error while fetching bus routes. status: " + response.getStatusCode());
        }
        List<Route> result = response.getBody().getResults().getMatches().values()
                .stream().map(match -> new Route(TransferType.BUS,
                        match.getDepartureStation(),
                        match.getArrivalStation(),
                        timeService.timeStringToDate(day, match.getStartTime()),
                        timeService.timeStringToDate(day, match.getArrivalTime())
                ))
                .toList();
        LOG.info("Successfully received routes for day: {}, from: {}, to: {} ({}) ", day, from, to, result.size());
        return result;
    }
}
