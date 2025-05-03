package com.example.exchange;

public interface ExchangeService {

    void acceptOrder(Order order);

    void registerStock(String ticker);
}
