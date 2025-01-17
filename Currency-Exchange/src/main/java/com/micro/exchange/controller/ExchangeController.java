package com.micro.exchange.controller;

import com.micro.exchange.beans.ExchangeValue;
import com.micro.exchange.repository.ExchangeRateRepo;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ExchangeController {
    
    @Autowired
    private Environment env;
    
    @Autowired
    private ExchangeRateRepo repo;
    
    Logger logger = LoggerFactory.getLogger(ExchangeController.class);
    
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue getExchangeValue(@PathVariable String from, @PathVariable String to){
        logger.info("GET /currency-exchange/from/{from}/to/{to} ");
        
        ExchangeValue exchangeInfo = repo.findByFromCurrencyAndToCurrency(from, to);
        
        logger.info("\tDid we make it?");
        return new ExchangeValue(exchangeInfo.getId(), exchangeInfo.getPort(), 
                                exchangeInfo.getConversionFactor(), from, to);
    }
}
