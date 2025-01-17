package com.micro.exchange.repository;

import com.micro.exchange.beans.ExchangeValue;
import java.math.BigDecimal;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExchangeRateRepo extends JpaRepository<ExchangeValue, BigDecimal>{
    public ExchangeValue findByFromCurrencyAndToCurrency(String fromCurrency, String toCurrency);
}
