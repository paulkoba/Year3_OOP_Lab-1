package com.aircorp.aircorp;

import java.util.Date;

public class Flight {
    public Flight(String id, Integer seats, Integer seat_price, Date date) {
        this.id = id;
        this.seats = seats;
        this.seat_price = seat_price;
        this.date = date;
    }

    public String id;
    public Integer seats;
    public Integer seat_price;
    public Date date;
}
