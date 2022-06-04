package com.paata.mikeladze.currency.controller;

import com.paata.mikeladze.currency.entity.Giphy;
import com.paata.mikeladze.currency.services.GiphyFeignServicesUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/giphy")
public class ControllerGiphy {
    @Value("${feign.giphy.key}")
    String keyGiphy;
    private final GiphyFeignServicesUtil giphyFeignServicesUtil;

    public ControllerGiphy(GiphyFeignServicesUtil giphyFeignServicesUtil) {
        this.giphyFeignServicesUtil = giphyFeignServicesUtil;
    }

    @GetMapping("/rich")
    public String richGiphy() {
        Giphy rich = giphyFeignServicesUtil.getGifRichOrBroke(keyGiphy, "rich");
        return (String) rich.getData().get("embed_url");
    }
    @GetMapping("/broke")
    public String brokeGiphy() {
        Giphy rich = giphyFeignServicesUtil.getGifRichOrBroke(keyGiphy, "broke");
        return (String) rich.getData().get("embed_url");
    }
}
