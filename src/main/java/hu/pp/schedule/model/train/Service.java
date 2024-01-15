package hu.pp.schedule.model.train;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
class Service {

    @JsonProperty("icon")
    private String icon;

    @JsonProperty("code")
    private int code;

    @JsonProperty("key")
    private String key;

}