package com.example.demo.service;

import com.example.demo.mapper.ChatMessageMapper;
import com.example.demo.model.ChatForm;
import com.example.demo.model.ChatMessage;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class MessageListService {
    private ChatMessageMapper chatMessageMapper;

    public MessageListService(ChatMessageMapper chatMessageMapper) {
        this.chatMessageMapper = chatMessageMapper;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Creating MessageService bean");
    }

    public void addChatMessage(ChatForm chatForm) {
        ChatMessage newChatMessage = new ChatMessage();
        newChatMessage.setUsername(chatForm.getUsername());
        switch (chatForm.getMessageType()) {
            case "Say":
                newChatMessage.setMessage(chatForm.getMessageText());
                break;
            case "Shout":
                newChatMessage.setMessage(chatForm.getMessageText().toUpperCase());
                break;
            case "Whisper":
                newChatMessage.setMessage(chatForm.getMessageText().toLowerCase());
                break;

        }
        chatMessageMapper.insert(newChatMessage);
    }

    public List<ChatMessage> getChatMessages() {
        return chatMessageMapper.getAllMessages();
    }
}
