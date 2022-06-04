package com.paata.mikeladze.currency.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@NoArgsConstructor
@Component
public class Giphy {
    private Map<String,Object> data;
}
