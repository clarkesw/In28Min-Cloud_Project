package com.clarke.micro.limits.controller;

import com.clarke.micro.limits.LimitsConfig;
import com.clarke.micro.limits.beans.Limits;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigController {
    
    @Autowired
    LimitsConfig config;
    
    Logger logger = LoggerFactory.getLogger(LimitsConfigController.class);
    
    @GetMapping("/config")
    public Limits getLimitConfigs(){
        logger.info("GET /config " + config);
        return new Limits(config.getMin(), config.getMax());
    }
    
//    @GetMapping("/test")
//    public String getTest(){
//        return "Test Limits";
//    }
}
