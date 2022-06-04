package com.paata.mikeladze.currency.services;

import com.paata.mikeladze.currency.entity.Exchanger;
import com.paata.mikeladze.currency.entity.Giphy;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "giphy",url = "${feign.giphy.api}")
public interface GiphyFeignServicesUtil {

    @GetMapping("/random")
        public Giphy getGifRichOrBroke(@RequestParam String api_key, @RequestParam String tag );

}
