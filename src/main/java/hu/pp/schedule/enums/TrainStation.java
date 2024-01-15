package hu.pp.schedule.enums;

public enum TrainStation {

    VAC("Vác"),
    NYUGATI("Budapest-Nyugati");

    private String value;

    TrainStation(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
