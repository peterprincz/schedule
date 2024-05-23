package hu.pp.schedule.model;

import hu.pp.schedule.enums.TransferType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Route {

    private TransferType transferType;
    private String from;
    private String to;
    private LocalDateTime departTime;
    private LocalDateTime arrivalTime;
    private String travelTime;
}
