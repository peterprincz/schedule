package hu.pp.schedule.enums;

public enum BusStation {

    ALTANYI_SZOLOK("Vác, Altányi szőlők", 67704),
    AUTOBUSZ_ALLOMAS("Vác, autóbusz-állomás", 18343),
    DEAKVARI_FOUT("Vác, Deákvári főút 29", 67702);

    private String value;
    private Integer lsId;

    BusStation(String value, Integer lsId) {
        this.value = value;
        this.lsId = lsId;
    }

    public String getValue() {
        return value;
    }

    public Integer getLsId() {
        return lsId;
    }
}
