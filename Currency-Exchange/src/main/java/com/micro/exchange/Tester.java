package com.micro.exchange;


public class Tester {
    public static void main(String[] args) {
        String time = String.valueOf(System.currentTimeMillis());
        System.out.println("Time: " + time.substring(time.length() - 4));
        
        String time2 = String.valueOf(System.currentTimeMillis());
        System.out.println("Time: " + time2.substring(time2.length() - 4));
    }
}
