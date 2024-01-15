package hu.pp.schedule.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BusRouteResponse {
    @JsonProperty("status")
    private String status;

    @JsonProperty("results")
    private BusRouteResponseResults results;

}
