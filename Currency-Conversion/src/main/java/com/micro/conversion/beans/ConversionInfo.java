/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.micro.conversion.beans;

import java.math.BigDecimal;


public class ConversionInfo {

    private int id;
    private Integer port;
    private int quantity;
    private BigDecimal conversionFactor;
    private BigDecimal totalAmount;
    private String fromCurrency;
    private String toCurrency; 
    
    public ConversionInfo() {}

    public ConversionInfo(int id, Integer port, int quantity, BigDecimal conversionFactor, String fromCurrency, String toCurrency) {
        this.id = id;
        this.port = port;
        this.quantity = quantity;
        this.conversionFactor = conversionFactor;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
    }

    public ConversionInfo(int id, Integer port, int quantity, BigDecimal conversionFactor, BigDecimal totalAmount, String fromCurrency, String toCurrency) {
        this.id = id;
        this.port = port;
        this.quantity = quantity;
        this.conversionFactor = conversionFactor;
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        
        if(totalAmount != null){
            this.totalAmount = totalAmount;
        }else{
            BigDecimal quanBD = new BigDecimal(quantity);
            this.totalAmount = quanBD.multiply(conversionFactor);
        }   
        
        System.out.println("Construtor Call: " + this.toString()); 
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;  
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getConversionFactor() {
        return conversionFactor;
    }

    public void setConversionFactor(BigDecimal conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    @Override
    public String toString() {
        return "ConversionInfo{" + "id=" + id + ", quantity=" + quantity + ", conversionFactor=" + conversionFactor + ", fromCurrency=" + fromCurrency + ", toCurrency=" + toCurrency + '}';
    }
}
