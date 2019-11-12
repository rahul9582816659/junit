package com.spring.junit.exception;

import java.sql.SQLException;

public class OrderProcessingException extends Exception {

    private static final long serialVersionUID = -5904554245788746761L;

    public OrderProcessingException(SQLException e) {
        super(e);
    }
}
