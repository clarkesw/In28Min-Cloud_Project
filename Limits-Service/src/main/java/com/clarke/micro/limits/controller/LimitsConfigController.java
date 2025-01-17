package com.clarke.micro.limits.controller;

import com.clarke.micro.limits.Configuration;
import com.clarke.micro.limits.beans.LimitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigController {
    
    @Autowired
    Configuration config;
    
    Logger logger = LoggerFactory.getLogger(LimitsConfigController.class);
    
    @GetMapping("/config")
    public LimitConfig getLimitConfigs(){
        logger.info("GET /config " + config.toString());
        return new LimitConfig(config.getMin(), config.getMax());
    }
}
