package com.example.exchange;

import java.util.Comparator;
import java.util.PriorityQueue;

public class OrderBook {
    PriorityQueue<Order> buyOrders = new PriorityQueue<Order>((a, b) ->
            Double.valueOf(b.getPrice()).compareTo(Double.valueOf(a.getPrice())));

    PriorityQueue<Order> sellOrders = new PriorityQueue<Order>(Comparator.comparing(o -> Double.valueOf(o.getPrice())));

    public void add(Order order) {
        if (order.getTransType().equals("BUY")) {
            // buyOrders.offer(order);
            this.matchBuyOrder(order);
        } else {
            // sellOrders.offer(order);
            this.matchSellOrder(order);
        }
    }

    public void matchBuyOrder(Order order) {
        while (!sellOrders.isEmpty()) {
            Order sellOrder = sellOrders.peek();
            if (sellOrder.getPrice() <= order.getPrice() && order.getTargetQty() > 0) {
                long qty = Math.min(order.getTargetQty(), sellOrder.getTargetQty());
                order.reduceQty(qty);
                sellOrder.reduceQty(qty);
                System.out.println(String.format("Matched Buy order stock: %s qty %s", order.getTicker(), qty));

                if (sellOrder.getTargetQty() == 0) {
                    sellOrders.poll();
                }

            } else {
                break;
            }
        }
        if (order.getTargetQty() > 0) {
            buyOrders.offer(order);
        }
    }

    public void matchSellOrder(Order order) {
        while (!buyOrders.isEmpty()) {
            Order buyOrder = buyOrders.peek();
            if (buyOrder.getPrice() >= order.getPrice() && order.getTargetQty() > 0) {
                long qty = Math.min(order.getTargetQty(), buyOrder.getTargetQty());
                long residual = buyOrder.getTargetQty() - qty;
                order.reduceQty(qty);
                buyOrder.reduceQty(qty);

                if (buyOrder.getTargetQty() == 0) {
                    buyOrders.poll();
                }

                System.out.println(String.format("Matched SELL order stock: %s qty %s", order.getTicker(), qty));

            } else {
                break;
            }
        }
        if (order.getTargetQty() > 0) {
            buyOrders.offer(order);
        }
    }

    public void printOrderBook() {
        System.out.println("Sell orders ");
        System.out.println("---------");
        this.sellOrders.stream().forEach(System.out::println);
        System.out.println("---------");
        this.buyOrders.stream().forEach(System.out::println);
        System.out.println("---------");
    }


}
