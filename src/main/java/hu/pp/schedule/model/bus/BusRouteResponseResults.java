package hu.pp.schedule.model.bus;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class BusRouteResponseResults {
    @JsonProperty("napkiiras")
    private String napkiiras;

    @JsonProperty("date_got")
    private String dateGot;

    @JsonProperty("daytype_got")
    private int dayTypeGot;

    @JsonProperty("apache_hostname")
    private String apacheHostname;

    @JsonProperty("talalatok")
    private Map<String, BusRouteResposponseMatches> matches;

}
