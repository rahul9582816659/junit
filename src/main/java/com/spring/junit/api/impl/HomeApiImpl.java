package com.spring.junit.api.impl;

import com.spring.junit.api.HomeApi;
import com.spring.junit.entity.Order;
import com.spring.junit.exception.OrderProcessingException;
import com.spring.junit.service.OrderProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class HomeApiImpl implements HomeApi {

    @Autowired
    private OrderProcessing orderProcessing;

    public void setOrderProcessing(OrderProcessing orderProcessing) {
        this.orderProcessing = orderProcessing;
    }

    @Override
    public String getHome() {
        return "App is up and running!";
    }

    @Override
    public Order getOrder(Order order) throws OrderProcessingException {
        boolean result = orderProcessing.placeOrder(order);
        return order;
    }


}
