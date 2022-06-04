package com.paata.mikeladze.currency;

import com.fasterxml.jackson.core.JsonFactoryBuilder;
import com.paata.mikeladze.currency.controller.ControllerCurrency;
import com.paata.mikeladze.currency.entity.Giphy;
import com.paata.mikeladze.currency.services.ExchangerFeignServicesUtil;
import com.paata.mikeladze.currency.services.GiphyFeignServicesUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
@AutoConfigureMockMvc
class CurrencyApplicationTests {
    @Value("${feign.exchanger.currency}")
    String currency;

    @Autowired
    MockMvc mockMvc;

    @Test
    void getRedirectionWithParametr() throws Exception {

        mockMvc.perform(get("/?currency="+ currency))
                .andExpect(status().is3xxRedirection());
    }
    @Test
    void getRedirectionWithOutParametr() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection());
    }
    @Test
    void getStatusOkWithGiphyRich() throws Exception {

        mockMvc.perform(get("/api/giphy/rich"))
                .andExpect(status().isOk());
    }
    @Test
    void getStatusOkWithGiphyBroke() throws Exception {

        mockMvc.perform(get("/api/giphy/broke"))
                .andExpect(status().isOk());
    }
    @Test
    void getStatusOkWithExchangerNow() throws Exception {

        mockMvc.perform(get("/api/exchanger/get/now/"))
                .andExpect(status().isOk());
    }
    @Test
    void getStatusOkWithExchangerYesterday() throws Exception {

        mockMvc.perform(get("/api/exchanger/get/yesterday/"))
                .andExpect(status().isOk());
    }







}
