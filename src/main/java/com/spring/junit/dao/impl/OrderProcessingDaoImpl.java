package com.spring.junit.dao.impl;

import com.spring.junit.dao.OrderProcessingDao;
import com.spring.junit.entity.Order;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public class OrderProcessingDaoImpl implements OrderProcessingDao {
    @Override
    public int createOrder(Order order) throws SQLException {
        return 1;
    }

    @Override
    public Order fetchOrder(int id) throws SQLException {
        return null;
    }

    @Override
    public int updateOrder(Order order) throws SQLException {
        return 1;
    }

    @Override
    public int deleteOrder(int id) throws SQLException {
        return 1;
    }
}
