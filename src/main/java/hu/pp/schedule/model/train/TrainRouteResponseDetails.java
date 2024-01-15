package hu.pp.schedule.model.train;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class TrainRouteResponseDetails {

    @JsonProperty("from")
    private String from;

    @JsonProperty("dep")
    private String departure;

    @JsonProperty("dep_real")
    private String realDeparture;

    @JsonProperty("platform")
    private String platform;

    @JsonProperty("train_info")
    private TrainRouteResponseInfo trainRouteResponseInfo;

    @JsonProperty("services")
    private List<TrainRouteResponseService> trainRouteResponseServices;

    @JsonProperty("original_way")
    private List<String> originalWay;

}