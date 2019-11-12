package com.spring.junit.api;

import com.spring.junit.entity.Order;
import com.spring.junit.exception.OrderProcessingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface HomeApi {

    @GetMapping("/home")
    String getHome();

    @PostMapping("/order")
    Order getOrder(@RequestBody Order order) throws OrderProcessingException;

}
