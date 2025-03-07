package com.clarke.micro.limits;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "limits-service") 
public class LimitsConfig {
    
    private int min;
    private int max;

    public LimitsConfig() {}

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMax(int max) {
        this.max = max;
    } 

    @Override
    public String toString() {
        return "Configuration{" + "min=" + min + ", max=" + max + '}';
    }
    
    
}
