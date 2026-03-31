package com.mycompany.assignment2.model;

public class OrderDetail {
    private int orderDetailId;
    private int orderId;
    private int productId;
    private double productPrice;
    private double discount;
    private int quantity;
    
    private String productName;

    public OrderDetail() {}

    public OrderDetail(int orderId, int productId, double productPrice, double discount, int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.productPrice = productPrice;
        this.discount = discount;
        this.quantity = quantity;
    }

    public int getOrderDetailId() { return orderDetailId; }
    public void setOrderDetailId(int orderDetailId) { this.orderDetailId = orderDetailId; }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public double getProductPrice() { return productPrice; }
    public void setProductPrice(double productPrice) { this.productPrice = productPrice; }

    public double getDiscount() { return discount; }
    public void setDiscount(double discount) { this.discount = discount; }
    
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
}
