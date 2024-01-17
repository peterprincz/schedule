package hu.pp.schedule.enums;

import lombok.Getter;

@Getter
public enum BusStation implements Station {

    ALTANYI_SZOLOK("Vác, Altányi szőlők", 67704),
    AUTOBUSZ_ALLOMAS("Vác, autóbusz-állomás", 18343),
    DEAKVARI_FOUT("Vác, Deákvári főút 29", 67702);

    private final String value;
    private final Integer lsId;

    BusStation(String value, Integer lsId) {
        this.value = value;
        this.lsId = lsId;
    }

}
