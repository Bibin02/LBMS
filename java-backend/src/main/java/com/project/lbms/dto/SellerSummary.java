package com.project.lbms.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SellerSummary {
    private String sellerUid;
    private String sellerName;
    private int totalBooks;
    private int soldBooks;
    private int onDelivery; 
    private double totalEarnings;

    private List<SalesData> salesData;

    private List<StockData> stockData;
}
