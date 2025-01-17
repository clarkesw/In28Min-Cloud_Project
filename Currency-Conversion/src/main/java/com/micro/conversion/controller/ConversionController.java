package com.micro.conversion.controller;

import com.micro.conversion.beans.ConversionInfo;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class ConversionController {
    
    @Autowired
    private RestTemplate rest;
    
    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public ConversionInfo getExchangeValue(@PathVariable String from, 
                                          @PathVariable String to,
                                          @PathVariable int quantity){
        
        String url = String.format("localhost:8100/currency-exchange/from/%s/to/%s", from, to);
        ResponseEntity<ConversionInfo> conversionEntity = rest.getForEntity(url, ConversionInfo.class);
        return conversionEntity.getBody();
    }
}
