package com.example.exchange;

import com.example.exchange.model.Order;
import com.example.exchange.model.OrderTransType;

import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

public class ExchangeImpl implements  Exchange {
    static int orderId = 0;
    PriorityQueue<Order> buyOrders = new PriorityQueue<>((o1, o2) ->
            Double.valueOf(o2.getPrice()).compareTo(Double.valueOf(o1.getPrice())));
    PriorityQueue<Order> sellOrders = new PriorityQueue<>(Comparator.comparing(o -> Double.valueOf(o.getPrice())));
    @Override
    public void placeOrder(Order order) {
        if (order.getTransType().equals(OrderTransType.BUY.name())) {
            Order sellOrder = sellOrders.peek();
            if (Objects.nonNull(sellOrder) && sellOrder.getPrice() <= order.getPrice() && sellOrder.getOrderQty() >= order.getOrderQty()) {
                System.out.println("Executed Buy order " + order.toString() + "Sell price: " + sellOrder.getPrice() );
                sellOrders.poll();
                long residual = sellOrder.getOrderQty() - order.getOrderQty();
                if ( residual > 0 ) {
                    sellOrders.offer(new Order(++orderId, sellOrder.getSecType(), sellOrder.getTransType(), residual, sellOrder.getPrice()));
                }
            } else {
                System.out.println("Unable to find a match. Adding to queue" + order.toString());
                buyOrders.offer(order);
            }
        }

        if (order.getTransType().equals(OrderTransType.SELL.name())) {
            Order buyOrder = buyOrders.peek();
            if (Objects.nonNull(buyOrder) &&  buyOrder.getPrice() >= order.getPrice() && buyOrder.getOrderQty() >= order.getOrderQty()) {
                System.out.println("Executed Sell order " + order.toString());
                buyOrders.poll();
                long residual = buyOrder.getOrderQty() - order.getOrderQty();
                if ( residual > 0 ) {
                    buyOrders.offer(new Order(++orderId, buyOrder.getSecType(), buyOrder.getTransType(), residual, buyOrder.getPrice()));
                }
            } else {
                System.out.println("Unable to find a match. Adding to queue" + order.toString());
                sellOrders.offer(order);
            }
        }
    }

    public static void main(String[] args) {
        Exchange exchange = new ExchangeImpl();
        Order order1 = new Order(++orderId, "BFUT", OrderTransType.SELL.name(), 100, 90);
        Order order2 = new Order(++orderId, "BFUT", OrderTransType.SELL.name(), 100, 89);
        Order order3 = new Order(++orderId, "BFUT", OrderTransType.BUY.name(), 100, 95);
        Order order4 = new Order(++orderId, "BFUT", OrderTransType.BUY.name(), 100, 91);

        exchange.placeOrder(order1);
        exchange.placeOrder(order2);
        exchange.placeOrder(order3);
        exchange.placeOrder(order4);

    }
}
