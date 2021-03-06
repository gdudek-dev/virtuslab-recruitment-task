package com.virtuslab.internship.controller.basket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtuslab.internship.VirtusLabApplication;
import com.virtuslab.internship.domain.basket.Basket;
import com.virtuslab.internship.domain.product.Product;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = VirtusLabApplication.class)
@WebAppConfiguration
class BasketControllerTest {

    protected MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Test
    void shouldReturnStatusOK() throws Exception {

        //Given
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        Basket basket = new Basket();
        basket.addProduct(new Product("Bread", Product.Type.GRAINS, BigDecimal.valueOf(2)));

        //When
            MvcResult result = mvc.perform(MockMvcRequestBuilders
                    .post("/api/basket")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(mapToJson(basket))).andReturn();

        //Then
        int status = result.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    void shouldReturnStatusBadRequest() throws Exception {

        //Given
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        Basket basket = new Basket();

        //When
        MvcResult result = mvc.perform(MockMvcRequestBuilders
                .post("/api/basket")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapToJson(basket))).andReturn();

        //Then
        int status = result.getResponse().getStatus();
        String message = result.getResponse().getContentAsString();
        assertEquals(400, status);
        assertEquals( "Basket is empty", message);
    }

    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}
