package com.project.lbms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SalesData {
    private int booksSold;
    private double perDayRevenue;
    private String dayOfWeek;
}
