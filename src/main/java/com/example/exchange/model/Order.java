package com.example.exchange.model;

public class Order {
    private int orderId;

    private String transType;

    private long orderQty;

    private double price;

    private String secType;

    public Order (int orderId, String secType, String transType, long orderQty, double price) {
        this.orderId = orderId;
        this.secType = secType;
        this.transType = transType;
        this.orderQty = orderQty;
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getTransType() {
        return transType;
    }

    public long getOrderQty() {
        return orderQty;
    }

    public double getPrice() {
        return price;
    }

    public String getSecType() {
        return secType;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", transType='" + transType + '\'' +
                ", orderQty=" + orderQty +
                ", price=" + price +
                '}';
    }
}
