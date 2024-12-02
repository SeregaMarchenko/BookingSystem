package com.example.bookingsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    private Long id;
    private Long eventId;
    private Long userId;
    private int seatNumber;
    private boolean isBooked;
}
