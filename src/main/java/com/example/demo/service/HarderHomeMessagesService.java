package com.example.demo.service;

import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class HarderHomeMessagesService {
    private List<String> messages;

    public List<String> getMessages() {
        return messages;
    }
    public void addMessage(String message) {
        messages.add(message);
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Creating HarderHomeMessagesService bean");
        this.messages = new ArrayList<>();
    }
}

