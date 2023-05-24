package com.aircorp.aircorp;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class Flight {
    private String id;
    private Integer seats;
    private Integer seatPrice;
    private Date date;
}
