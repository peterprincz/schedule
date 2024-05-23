package hu.pp.schedule.model.bus;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BusRouteResposponseMatches {
    @JsonProperty("departureStation")
    private String departureStation;
    @JsonProperty("arrivalStation")
    private String arrivalStation;
    @JsonProperty("indulasi_ido")
    private String departTime;
    @JsonProperty("erkezesi_ido")
    private String arrivalTime;
    @JsonProperty("osszido")
    private String travelTime;
    @JsonProperty("atszallasok_szama")
    private Integer numberOfChanges;

}
