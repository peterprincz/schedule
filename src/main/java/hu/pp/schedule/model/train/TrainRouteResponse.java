package hu.pp.schedule.model.train;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TrainRouteResponse {


    @JsonProperty("timetable")
    private List<TimetableEntry> timetable;

    @JsonProperty("date")
    private String date;

    @JsonProperty("route")
    private String route;


}