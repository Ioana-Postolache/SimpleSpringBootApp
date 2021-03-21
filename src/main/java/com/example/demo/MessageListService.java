package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageListService {
    List<String> messages = new ArrayList<>();
    List<ChatMessage> chatMessages = new ArrayList<>();

    public List<String> getMessages() {
        return messages;
    }
    public void addMessage(String message) {
        messages.add(message);
    }
    
    public List<ChatMessage> getChatMessages() {
        return chatMessages;
    }
    public void addChatMessages(ChatMessage chatMessage) {
        chatMessages.add(chatMessage);
    }
}
