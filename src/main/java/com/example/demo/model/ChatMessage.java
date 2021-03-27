package com.example.demo.model;

public class ChatMessage {
    private Integer messageId;
    private String username;
    private String messageText;

    public ChatMessage() {
    }

    public ChatMessage(Integer messageId, String username, String messageText) {
        this.messageId = messageId;
        this.username = username;
        this.messageText = messageText;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return messageText;
    }

    public void setMessage(String message) {
        this.messageText = messageText;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }
}
