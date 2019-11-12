package com.spring.junit.service.impl;

import com.spring.junit.dao.OrderProcessingDao;
import com.spring.junit.entity.Order;
import com.spring.junit.exception.OrderProcessingException;
import com.spring.junit.service.OrderProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class OrderProcessingImpl implements OrderProcessing {

    @Autowired
    private OrderProcessingDao orderProcessingDao;

    public void setOrderProcessingDao(OrderProcessingDao orderProcessingDao) {
        this.orderProcessingDao = orderProcessingDao;
    }

    @Override
    public boolean placeOrder(Order order) throws OrderProcessingException {
        try {
            int result = orderProcessingDao.createOrder(order);
            if (result == 0) {
                return false;
            }
        } catch (SQLException e) {
            throw new OrderProcessingException(e);
        }

        return true;

    }

    @Override
    public boolean cancelOrder(int id) throws OrderProcessingException {

        try {
            Order order = orderProcessingDao.fetchOrder(id);
            order.setStatus("cancelled");

            int result = orderProcessingDao.updateOrder(order);

            if (result == 0) {
                return false;
            }
        } catch (SQLException e) {
            throw new OrderProcessingException(e);
        }

        return true;
    }

    @Override
    public boolean deleteOrder(int id) throws OrderProcessingException {
        try {
            int result = orderProcessingDao.deleteOrder(id);

            if (result == 0) {
                return false;
            }
        } catch (SQLException e) {
            throw new OrderProcessingException(e);
        }

        return true;
    }
}
