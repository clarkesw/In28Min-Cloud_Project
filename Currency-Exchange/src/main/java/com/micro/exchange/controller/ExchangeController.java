package com.micro.exchange.controller;

import static com.micro.exchange.AppConstants.APP_ID;
import com.micro.exchange.beans.ExchangeValue;
import com.micro.exchange.repository.ExchangeRateRepo;
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
        //int port = Integer.parseInt(env.getProperty("server.port"));
        logger.info("GET " + APP_ID + "/currency-exchange/from/{from}/to/{to} ");
        
        ExchangeValue exchangeInfo = repo.findByFromCurrencyAndToCurrency(from, to);
        
        return new ExchangeValue(exchangeInfo.getId(), APP_ID, 
                                exchangeInfo.getConversionFactor(), from, to);
    }
}
