package com.paata.mikeladze.currency.controller;

import com.paata.mikeladze.currency.entity.Exchanger;
import com.paata.mikeladze.currency.services.ExchangerFeignServicesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Period;
import java.util.Iterator;
@RestController
@RequestMapping("/api/exchanger")
@Slf4j
public class ControlerExchange {
    /*ID exchanger*/
    @Value("${feign.exchanger.key}")
    String keyExchanger;
    private final ExchangerFeignServicesUtil exchangerFeignServicesUtil;

    public ControlerExchange(ExchangerFeignServicesUtil exchangerFeignServicesUtil) {
        this.exchangerFeignServicesUtil = exchangerFeignServicesUtil;
    }

    /*Получаем значение */
    @GetMapping("/get/now/")
    public Double getCurrencyNow(@RequestParam(defaultValue = "${feign.exchanger.currency}", required = false) String currency) {
        /*Значение валюты за сегодняшний день день*/
        Exchanger valueNow = exchangerFeignServicesUtil.getValueNow(keyExchanger, currency);
        Iterator<Double> iterator = valueNow.getRates().values().iterator();
        Double currencyValueNOw = valueNow.getRates().values().iterator().next();
        return currencyValueNOw;

    }

    @GetMapping("/get/yesterday/")
    public Double getCurrencyYesterday(@RequestParam(defaultValue = "${feign.exchanger.currency}", required = false) String currency) {
        /*Значение валюты за вчерашний день*/
        Exchanger valueYesterday = exchangerFeignServicesUtil.getValueYesterday(String.valueOf(LocalDate.now().minus(Period.ofDays(1))), keyExchanger, currency);
        Double currencyValueYesterday = valueYesterday.getRates().values().iterator().next();

        return currencyValueYesterday;

    }
}
