package com.spring.junit.basic.impl;

import com.spring.junit.basic.Greeting;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GreetingImplTest {

    private Greeting greeting;

    @BeforeEach
    public void setup() {
        greeting = new GreetingImpl();
    }

    @AfterEach
    public void teardown() {
        greeting = null;
    }


    @Test
    void greetShouldReturnValidOutput() {
        String result = greeting.greet("junit");

        assertNotNull(result);
        assertEquals("Hello junit", result);
    }

    @Test()
    void greetShouldThrowException_null() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
           greeting.greet(null);
        });
    }

    @Test()
    void greetShouldThrowException_blank() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            greeting.greet("");
        });
    }
}
