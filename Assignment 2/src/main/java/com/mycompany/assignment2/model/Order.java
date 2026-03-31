package com.mycompany.assignment2.model;

import java.sql.Timestamp;

public class Order {
    private int orderId;
    private Timestamp orderDate;
    private String sessionId;
    private int userId;
    private String paymentMode;
    private double tax;
    private double totalAmount;
    private String orderStatus;

    public Order() {}

    public Order(String sessionId, int userId, String paymentMode, double tax, double totalAmount, String orderStatus) {
        this.sessionId = sessionId;
        this.userId = userId;
        this.paymentMode = paymentMode;
        this.tax = tax;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
    }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public Timestamp getOrderDate() { return orderDate; }
    public void setOrderDate(Timestamp orderDate) { this.orderDate = orderDate; }

    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getPaymentMode() { return paymentMode; }
    public void setPaymentMode(String paymentMode) { this.paymentMode = paymentMode; }

    public double getTax() { return tax; }
    public void setTax(double tax) { this.tax = tax; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public String getOrderStatus() { return orderStatus; }
    public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }
}
