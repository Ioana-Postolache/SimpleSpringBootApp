package com.example.demo.service;

import com.example.demo.model.ChatForm;
import com.example.demo.model.ChatMessage;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageListService {
    private List<String> messages;
    private List<ChatMessage> chatMessages;


    public List<String> getMessages() {
        return messages;
    }
    public void addMessage(String message) {
        messages.add(message);
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Creating MessageService bean");
        this.messages = new ArrayList<>();
        this.chatMessages = new ArrayList<>();
    }

    public void addChatMessage(ChatForm chatForm) {
        ChatMessage newMessage = new ChatMessage();
        newMessage.setUsername(chatForm.getUsername());
        switch (chatForm.getMessageType()) {
            case "Say":
                newMessage.setMessage(chatForm.getMessageText());
                break;
            case "Shout":
                newMessage.setMessage(chatForm.getMessageText().toUpperCase());
                break;
            case "Whisper":
                newMessage.setMessage(chatForm.getMessageText().toLowerCase());
                break;
        }
        this.chatMessages.add(newMessage);
    }

    public List<ChatMessage> getChatMessages() {
        return chatMessages;
    }
}
