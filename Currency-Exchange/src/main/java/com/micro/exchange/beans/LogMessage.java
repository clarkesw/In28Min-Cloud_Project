package com.micro.exchange.beans;


public class LogMessage {
    private String level;
    private String message;
    private String timestamp;

    public LogMessage() {
    }

    public LogMessage(String level, String message, String timestamp) {
        this.level = level;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "LogMessage{" + "level=" + level + ", message=" + message + ", timestamp=" + timestamp + '}';
    }
}
