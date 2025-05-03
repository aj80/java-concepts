package com.example.exchange;

public class Order {
    private String transType;

    private String ticker;

    private long orderId;

    private long targetQty;

    private double price;

    public Order(long orderId, String transType, String ticker, long targetQty, double limitPrice) {
        this.orderId = orderId;
        this.transType = transType;
        this.ticker = ticker;
        this.targetQty = targetQty;
        this.price = limitPrice;
    }

    public String getTransType() {
        return transType;
    }

    public String getTicker() {
        return ticker;
    }

    public long getOrderId() {
        return orderId;
    }

    public long getTargetQty() {
        return targetQty;
    }

    public double getPrice() {
        return price;
    }

    public void reduceQty(long qty) {
        this.targetQty -= qty;
    }

    @Override
    public String toString() {
        return "Order{" +
                "transType='" + transType + '\'' +
                ", ticker='" + ticker + '\'' +
                ", orderId=" + orderId +
                ", targetQty=" + targetQty +
                ", price=" + price +
                '}';
    }
}
