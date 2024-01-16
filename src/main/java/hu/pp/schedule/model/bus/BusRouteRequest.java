package hu.pp.schedule.model.bus;


import com.fasterxml.jackson.annotation.JsonProperty;
import hu.pp.schedule.enums.BusStation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusRouteRequest {

    private String func;
    private Params params;

    public static BusRouteRequest create(Date day, BusStation from, BusStation to) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return BusRouteRequest.builder()
                .func("getRoutes")
                .params(
                        Params.builder()
                                .networks(List.of(1, 2, 3, 10, 11, 12, 13, 14, 24, 25, 26))
                                .datum(sdf.format(day))
                                .erkStype("megallo")
                                .extSettings("block")
                                .helyi("No")
                                .honnan(from.getValue())
                                .honnanLsId(from.getLsId())
                                .honnanSettlementId(2493)
                                .honnanSiteCode("2493F1")
                                .hour("0")
                                .hova(to.getValue())
                                .hovaLsId(to.getLsId())
                                .hovaSettlementId(2493)
                                .hovaSiteCode("2493F1")
                                .indStype("megallo")
                                .keresztulStype("megallo")
                                .maxatszallas("5")
                                .maxvar("240")
                                .maxwalk("1000")
                                .min("54")
                                .napszak(3)
                                .naptipus(0)
                                .odavissza(0)
                                .preferencia("0")
                                .rendezes("1")
                                .submitted(1)
                                .talalatok(1)
                                .target(0)
                                .utirany("oda")
                                .var("0")
                                .build()
                )
                .build();
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Params {

        @JsonProperty("networks")
        private List<Integer> networks;

        @JsonProperty("datum")
        private String datum;

        @JsonProperty("erk_stype")
        private String erkStype;

        @JsonProperty("ext_settings")
        private String extSettings;

        @JsonProperty("filtering")
        private int filtering;

        @JsonProperty("helyi")
        private String helyi;

        @JsonProperty("honnan")
        private String honnan;

        @JsonProperty("honnan_ls_id")
        private int honnanLsId;

        @JsonProperty("honnan_settlement_id")
        private int honnanSettlementId;

        @JsonProperty("honnan_site_code")
        private String honnanSiteCode;

        @JsonProperty("hour")
        private String hour;

        @JsonProperty("hova")
        private String hova;

        @JsonProperty("hova_ls_id")
        private int hovaLsId;

        @JsonProperty("hova_settlement_id")
        private int hovaSettlementId;

        @JsonProperty("hova_site_code")
        private String hovaSiteCode;

        @JsonProperty("ind_stype")
        private String indStype;

        @JsonProperty("keresztul_stype")
        private String keresztulStype;

        @JsonProperty("maxatszallas")
        private String maxatszallas;

        @JsonProperty("maxvar")
        private String maxvar;

        @JsonProperty("maxwalk")
        private String maxwalk;

        @JsonProperty("min")
        private String min;

        @JsonProperty("napszak")
        private int napszak;

        @JsonProperty("naptipus")
        private int naptipus;

        @JsonProperty("odavissza")
        private int odavissza;

        @JsonProperty("preferencia")
        private String preferencia;

        @JsonProperty("rendezes")
        private String rendezes;

        @JsonProperty("submitted")
        private int submitted;

        @JsonProperty("talalatok")
        private int talalatok;

        @JsonProperty("target")
        private int target;

        @JsonProperty("utirany")
        private String utirany;

        @JsonProperty("var")
        private String var;
    }
}
