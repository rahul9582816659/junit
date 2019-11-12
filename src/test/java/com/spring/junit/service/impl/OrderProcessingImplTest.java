package com.spring.junit.service.impl;

import com.spring.junit.dao.OrderProcessingDao;
import com.spring.junit.entity.Order;
import com.spring.junit.exception.OrderProcessingException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrderProcessingImplTest {

    @Mock
    private OrderProcessingDao orderProcessingDao;

    private OrderProcessingImpl orderProcessing;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        orderProcessing = new OrderProcessingImpl();
        orderProcessing.setOrderProcessingDao(orderProcessingDao);
    }

    @AfterEach
    void tearDown() {
        orderProcessing = null;
    }

    @Test
    void placeOrder_shouldCreateOrder() throws SQLException, OrderProcessingException {

        Order order = new Order();
        when(orderProcessingDao.createOrder(any(Order.class))).thenReturn(1);

        boolean result = orderProcessing.placeOrder(order);

        verify(orderProcessingDao).createOrder(order);
        assertTrue(result);
    }

    @Test
    void placeOrder_shouldNotCreateOrder() throws SQLException, OrderProcessingException {

        Order order = new Order();
        when(orderProcessingDao.createOrder(any(Order.class))).thenReturn(0);

        boolean result = orderProcessing.placeOrder(order);

        verify(orderProcessingDao).createOrder(order);
        assertFalse(result);
    }

    @Test
    void placeOrder_shouldThrowSQLException() throws SQLException {

        Order order = new Order();
        when(orderProcessingDao.createOrder(any(Order.class))).thenThrow(SQLException.class);

        Assertions.assertThrows(OrderProcessingException.class, () -> {
           orderProcessing.placeOrder(order);
        });

    }

    @Test
    void cancelOrder_shouldCancelOrder() throws SQLException, OrderProcessingException {

        Order order = new Order();
        when(orderProcessingDao.fetchOrder(anyInt())).thenReturn(order);
        when(orderProcessingDao.updateOrder(any(Order.class))).thenReturn(1);

        boolean result = orderProcessing.cancelOrder(1);

        verify(orderProcessingDao).fetchOrder(1);
        verify(orderProcessingDao).updateOrder(order);
        assertTrue(result);

    }

    @Test
    void cancelOrder_shouldNotCancelOrder() throws SQLException, OrderProcessingException {

        Order order = new Order();
        when(orderProcessingDao.fetchOrder(anyInt())).thenReturn(order);
        when(orderProcessingDao.updateOrder(any(Order.class))).thenReturn(0);

        boolean result = orderProcessing.cancelOrder(1);

        verify(orderProcessingDao).fetchOrder(1);
        verify(orderProcessingDao).updateOrder(order);
        assertFalse(result);

    }

    @Test
    void cancelOrder_shouldThrowException_fetchOrder() throws SQLException {

        when(orderProcessingDao.fetchOrder(anyInt())).thenThrow(SQLException.class);
        when(orderProcessingDao.updateOrder(any(Order.class))).thenReturn(0);

        Assertions.assertThrows(OrderProcessingException.class, () -> {
            orderProcessing.cancelOrder(1);
        });

    }

    @Test
    void cancelOrder_shouldThrowException_updateOrder() throws SQLException {

        Order order = new Order();
        when(orderProcessingDao.fetchOrder(anyInt())).thenReturn(order);
        when(orderProcessingDao.updateOrder(any(Order.class))).thenThrow(SQLException.class);

        Assertions.assertThrows(OrderProcessingException.class, () -> {
            orderProcessing.cancelOrder(1);
        });

    }

    @Test
    void deleteOrder_shouldDeleteOrder() throws SQLException, OrderProcessingException {

        when(orderProcessingDao.deleteOrder(anyInt())).thenReturn(1);

        boolean result = orderProcessing.deleteOrder(1);

        verify(orderProcessingDao).deleteOrder(1);
        assertTrue(result);
    }

    @Test
    void deleteOrder_shouldNotDeleteOrder() throws SQLException, OrderProcessingException {

        when(orderProcessingDao.deleteOrder(anyInt())).thenReturn(0);

        boolean result = orderProcessing.deleteOrder(1);

        verify(orderProcessingDao).deleteOrder(1);
        assertFalse(result);
    }

    @Test
    void deleteOrder_shouldThrowException() throws SQLException {

        when(orderProcessingDao.deleteOrder(anyInt())).thenThrow(SQLException.class);

        Assertions.assertThrows(OrderProcessingException.class, () -> {
            orderProcessing.deleteOrder(1);
        });
    }
}
