package com.spring.junit.dao;

import com.spring.junit.entity.Order;

import java.sql.SQLException;

public interface OrderProcessingDao {
    int createOrder(Order order) throws SQLException;
    Order fetchOrder(int id) throws SQLException;
    int updateOrder(Order order) throws SQLException;
    int deleteOrder(int id) throws SQLException;
}
