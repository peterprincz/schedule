package hu.pp.schedule.enums;

import lombok.Getter;

@Getter
public enum TrainStation {

    VAC("Vác"),
    NYUGATI("Budapest-Nyugati");

    private final String value;

    TrainStation(String value) {
        this.value = value;
    }

}
