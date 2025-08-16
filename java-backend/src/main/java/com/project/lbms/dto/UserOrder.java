package com.project.lbms.dto;

public interface UserOrder {

    public String getOrderId();
    public String getOrderStatus();
    public double getTotalPrize();
    public int getTotalBooks();
    public int getLendBooksCount();
    public long getOrderTime();

}
