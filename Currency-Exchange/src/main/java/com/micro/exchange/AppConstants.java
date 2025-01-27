package com.micro.exchange;


public class AppConstants {
    public static final int APP_ID;
    static{ 
        String time = String.valueOf(System.currentTimeMillis());
        APP_ID = Integer.parseInt(time.substring(time.length() - 4));
    
    }
}