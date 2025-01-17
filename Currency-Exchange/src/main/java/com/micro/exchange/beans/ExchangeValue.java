package com.micro.exchange.beans;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name="Exchange")
public class ExchangeValue {
    
    @Id
    private int id;
    @Column(nullable = true)
    private Integer port;
    private BigDecimal conversionFactor;
    private String fromCurrency;
    private String toCurrency;
        
    public ExchangeValue() {}
    
    public ExchangeValue(ExchangeValue exchangeInfo) {
        this.id = exchangeInfo.getId();
        this.port = exchangeInfo.getPort();
        this.conversionFactor = exchangeInfo.getConversionFactor();
        this.fromCurrency = exchangeInfo.getFrom();
        this.toCurrency = exchangeInfo.getTo();
    }
        
    public ExchangeValue(int id, BigDecimal conversionFactor, String from, String to) {
        this.id = id;
        this.conversionFactor = conversionFactor;
        this.fromCurrency = from;
        this.toCurrency = to;
    }

    public ExchangeValue(int id, Integer port, BigDecimal conversionFactor, String from, String to) {
        this.id = id;
        this.port = port;
        this.conversionFactor = conversionFactor;
        this.fromCurrency = from;
        this.toCurrency = to;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getFrom() {
        return fromCurrency;
    }

    public void setFrom(String from) {
        this.fromCurrency = from;
    }

    public String getTo() {
        return toCurrency;
    }

    public void setTo(String to) {
        this.toCurrency = to;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getConversionFactor() {
        return conversionFactor;
    }

    public void setConversionFactor(BigDecimal conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    @Override
    public String toString() {
        return "ExchangeValue{" + "id=" + id + ", port=" + port + ", conversionFactor=" + conversionFactor + ", fromCurrency=" + fromCurrency + ", toCurrency=" + toCurrency + '}';
    }
}
