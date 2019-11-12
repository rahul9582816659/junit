package com.spring.junit.basic.impl;

import com.spring.junit.basic.Greeting;

public class GreetingImpl implements Greeting {

    @Override
    public String greet(String message) {
        if (message == null || message.length() == 0) {
            throw new IllegalArgumentException();
        }
        return "Hello " + message;
    }
}
