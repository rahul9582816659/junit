package com.spring.junit.service;

import com.spring.junit.entity.Order;
import com.spring.junit.exception.OrderProcessingException;

public interface OrderProcessing {
    boolean placeOrder(Order order) throws OrderProcessingException;
    boolean cancelOrder(int id) throws OrderProcessingException;
    boolean deleteOrder(int id) throws OrderProcessingException;
}
