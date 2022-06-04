package com.paata.mikeladze.currency.controller;

import com.fasterxml.jackson.core.json.JsonReadContext;
import com.paata.mikeladze.currency.entity.Exchanger;
import com.paata.mikeladze.currency.entity.Giphy;
import com.paata.mikeladze.currency.services.ExchangerFeignServicesUtil;
import com.paata.mikeladze.currency.services.GiphyFeignServicesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Iterator;
import java.util.Objects;

@RestController
@Slf4j
public class ControllerCurrency {
    /*ID exchanger*/
    @Value("${feign.exchanger.key}")
    String keyExchanger;
    @Value("${feign.giphy.key}")
    String keyGiphy;

    /*Feign clients*/
    private final GiphyFeignServicesUtil giphyFeignServicesUtil;
    private final ExchangerFeignServicesUtil exchangerFeignServicesUtil;

    public ControllerCurrency(ExchangerFeignServicesUtil feignServicesUtil, GiphyFeignServicesUtil giphyFeignServicesUtil) {
        this.exchangerFeignServicesUtil = feignServicesUtil;
        this.giphyFeignServicesUtil = giphyFeignServicesUtil;
    }

    @GetMapping("/")
    public void handle(HttpServletResponse response, @RequestParam(defaultValue = "${feign.exchanger.currency}",required = false) String currency) throws IOException {
        /*Значение валюты за сегодняшний день день*/
        Exchanger valueNow = exchangerFeignServicesUtil.getValueNow(keyExchanger, currency);
        /*Значение валюты за вчерашний день*/
        Exchanger valueYesterday = exchangerFeignServicesUtil.getValueYesterday(String.valueOf(LocalDate.now().minus(Period.ofDays(1))), keyExchanger, currency);

        log.info("dateNow= "+LocalDate.now()+" dateYesterday="+ String.valueOf(LocalDate.now().minus(Period.ofDays(1))));
        Iterator<Double> iterator = valueNow.getRates().values().iterator();
        Iterator<Double> iteratorYesterday = valueYesterday.getRates().values().iterator();
        /*Если итераторы содержат значения*/
        if (iterator.hasNext() && iteratorYesterday.hasNext()) {
            Double currencyValueNOw = iterator.next();
            Double currencyValueYesterday = iteratorYesterday.next();
            log.info("currencyValueNOw = " + currencyValueNOw + " and currencyValueYesterday = " + currencyValueYesterday);
            /*3метода сравнения*/
             if (currencyValueNOw > currencyValueYesterday) {
                Giphy rich = giphyFeignServicesUtil.getGifRichOrBroke(keyGiphy, "rich");
                Object embed_url = rich.getData().get("embed_url");
                log.info("currencyValueNOw : " + currencyValueNOw + " > currencyValueYesterday : " + currencyValueYesterday);
                response.sendRedirect((String) embed_url);
            }
            else if (currencyValueNOw < currencyValueYesterday) {
                Giphy broke = giphyFeignServicesUtil.getGifRichOrBroke(keyGiphy, "broke");
                Object embed_url = broke.getData().get("embed_url");
                log.info("currencyValueNOw : " + currencyValueNOw + " < currencyValueYesterday : " + currencyValueYesterday);
                response.sendRedirect((String) embed_url);
            }
            else if (currencyValueNOw.equals(currencyValueYesterday)) {
                log.info("currencyValueNOw : " + currencyValueNOw + " = currencyValueYesterday : " + currencyValueYesterday);
                response.sendRedirect("https://media4.giphy.com/media/PjaTjG8ct5cNOtjjZg/giphy.gif?cid=ecf05e47xjgdvce0o8zn1h25y58guxkwx8cvnhsjvzu9smzp&rid=giphy.gif&ct=g");
            } else {
                log.warn("You have warning, please check settings yml or code");
            }

        } else {
            log.warn("Warning in yor Request");
        }
    }





}
