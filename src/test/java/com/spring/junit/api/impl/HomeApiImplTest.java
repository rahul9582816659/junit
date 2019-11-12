package com.spring.junit.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.junit.entity.Order;
import com.spring.junit.service.OrderProcessing;
import com.spring.junit.service.impl.OrderProcessingImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class HomeApiImplTest {

    private MockMvc mockMvc;

    @InjectMocks
    private HomeApiImpl homeApi;

    private ObjectMapper om;

    @Mock
    private OrderProcessing orderProcessing;

    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders.standaloneSetup(homeApi).build();
        MockitoAnnotations.initMocks(this);

        orderProcessing = new OrderProcessingImpl();
        homeApi.setOrderProcessing(orderProcessing);

        om = new ObjectMapper();
    }

    @AfterEach
    void tearDown() {
        mockMvc = null;
    }

    @Test
    void getHome() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/home"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("App is up and running!"));
    }

    @Test
    void getOrder() throws Exception {

        // when(orderProcessing.placeOrder(any(Order.class))).thenReturn(true);

        Order order = new Order();
        order.setId(1);
        order.setStatus("available");

        String postOrder = om.writeValueAsString(order);


        mockMvc.perform(MockMvcRequestBuilders.post("/order").content(postOrder).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
                /*.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is("1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.status", Matchers.is("available")));*/

    }
}
