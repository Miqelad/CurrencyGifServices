package com.paata.mikeladze.currency.services;

import com.paata.mikeladze.currency.entity.Exchanger;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@FeignClient(value = "currency", url = "${feign.exchanger.api}")
public interface ExchangerFeignServicesUtil {

    @GetMapping("/latest.json")
    public Exchanger getValueNow(@RequestParam String app_id, @RequestParam String symbols );

    @GetMapping("/historical/{yesterday}.json")
    public Exchanger getValueYesterday(@PathVariable String yesterday, @RequestParam String app_id, @RequestParam String symbols );
}
