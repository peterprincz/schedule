package hu.pp.schedule.service;

import hu.pp.schedule.entity.Route;
import hu.pp.schedule.enums.BusStation;
import hu.pp.schedule.enums.TransferType;
import hu.pp.schedule.model.bus.BusRouteRequest;
import hu.pp.schedule.model.bus.BusRouteResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class BusRouteScrapingService {

    private static final Logger LOG = LoggerFactory.getLogger(BusRouteScrapingService.class);

    private static final String URL = "https://menetrendek.hu/menetrend/newinterface/index.php";

    public List<Route> getRoutes(Date day, BusStation from, BusStation to) {
        LOG.info("Getting routes for day: {}, from: {}, to: {}", day, from, to);
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
                        timeStringToDate(day, match.getStartTime()),
                        timeStringToDate(day, match.getArrivalTime())
                ))
                .toList();
        LOG.info("Successfully received routes for day: {}, from: {}, to: {} ({}) ", day, from, to, result.size());
        return result;
    }

    private Date timeStringToDate(Date day, String time) {
        Calendar date = Calendar.getInstance();
        date.setTime(day);
        date.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time.substring(0, 2)));
        date.set(Calendar.MINUTE, Integer.parseInt(time.substring(3, 5)));
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);
        return date.getTime();
    }
}
