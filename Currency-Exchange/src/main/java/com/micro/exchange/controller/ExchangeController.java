package com.micro.exchange.controller;

import static com.micro.exchange.AppConstants.APP_ID;
import com.micro.exchange.beans.ExchangeValue;
import com.micro.exchange.repository.ExchangeRateRepo;
import com.micro.exchange.service.LoggingService;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExchangeController {

//    @Autowired
//    private Environment env;

    @Autowired
    private ExchangeRateRepo repo;

    @Autowired
    private MeterRegistry meterRegistry;

    @Autowired
    private LoggingService logServ;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue getExchangeValue(@PathVariable String from, @PathVariable String to) {
        //int port = Integer.parseInt(env.getProperty("server.port"));
        logServ.info("GET " + APP_ID + "/currency-exchange/from/{from}/to/{to} ");

        Timer timer = meterRegistry.timer("currency_exchange_request", "from", from, "to", to);
        return timer.record(() -> {
            ExchangeValue exchangeInfo = repo.findByFromCurrencyAndToCurrency(from, to);
            return new ExchangeValue(exchangeInfo.getId(), APP_ID, exchangeInfo.getConversionFactor(), from, to);
        });
    } 
    
     @GetMapping("/test")
     public String getMe(){
         logServ.info("GET " + "/test ");
         return "Big Testn'";
     }
}
