package hu.pp.schedule.model.train;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class TrainRouteResponseInfo {

    @JsonProperty("href")
    private String href;

    @JsonProperty("url")
    private String url;

    @JsonProperty("get_url")
    private String getUrl;

    @JsonProperty("code")
    private String code;

    @JsonProperty("text")
    private String text;

    @JsonProperty("info")
    private String info;

    @JsonProperty("type")
    private String type;

    @JsonProperty("is_local_transport")
    private boolean isLocalTransport;

}