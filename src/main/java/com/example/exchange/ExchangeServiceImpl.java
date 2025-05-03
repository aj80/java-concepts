package com.example.exchange;

import java.util.HashMap;
import java.util.Map;

public class ExchangeServiceImpl implements ExchangeService {

     private static Map<String, OrderBook> orderBook = new HashMap<>();

    public void registerStock(String ticker) {
        if (!orderBook.containsKey(ticker)) {
            orderBook.put(ticker, new OrderBook());
        }
    }

    public void acceptOrder(Order order) {
        orderBook.get(order.getTicker()).add(order);
    }

    public static void main(String[] args) {
        ExchangeService exService = new ExchangeServiceImpl();
        exService.registerStock("NVIDIA");

        Order buyOrder1 = new Order(1, "BUY", "NVIDIA", 100, 99);

        Order sellOrder1 = new Order(1, "SELL", "NVIDIA", 90, 97);

        exService.acceptOrder(buyOrder1);
        exService.acceptOrder(sellOrder1);
        orderBook.get("NVIDIA").printOrderBook();

    }
}
