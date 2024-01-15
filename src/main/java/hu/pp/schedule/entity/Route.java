package hu.pp.schedule.entity;

import hu.pp.schedule.enums.TransferType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
public class Route {

    @Enumerated(EnumType.STRING)
    private TransferType transferType;
    private String from;
    private String to;
    private Date start;
    private Date arrival;
}
