package hu.pp.schedule.model.train;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class TimetableEntry {

    @JsonProperty("type")
    private String type;

    @JsonProperty("starttime")
    private String startTime;

    @JsonProperty("start")
    private String start;

    @JsonProperty("destinationtime")
    private String destinationTime;

    @JsonProperty("destination")
    private String destination;

    @JsonProperty("change")
    private String change;

    @JsonProperty("totaltime")
    private String totalTime;

    @JsonProperty("distance")
    private String distance;

    @JsonProperty("cost1st")
    private String cost1st;

    @JsonProperty("cost2nd")
    private String cost2nd;

    @JsonProperty("class")
    private String trainClass;

    @JsonProperty("class_name")
    private String className;

    @JsonProperty("details")
    private List<TimetableDetails> details;

}
