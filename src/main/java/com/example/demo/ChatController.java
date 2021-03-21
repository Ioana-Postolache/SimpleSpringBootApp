package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat")
public class ChatController {
    private MessageListService messageListService;


    public ChatController(MessageListService messageListService) {
        this.messageListService = messageListService;
    }

    @GetMapping()
    public String getChatMessages(ChatForm chatForm, Model model) {
        model.addAttribute("chats", messageListService.getChatMessages());
        return "chat";
    }

    @PostMapping()
    public String addChatMessages(ChatForm chatForm, Model model) {
        messageListService.addChatMessages(new ChatMessage(chatForm.getUsername(),
                transformMessage(chatForm.getMessageType(), chatForm.getMessageText())));
        model.addAttribute("chats", messageListService.getChatMessages());
        return "chat";
    }

    public String transformMessage(String messageType, String message) {
        String transformedMessage;
        switch (messageType) {
            case "Shout":
                transformedMessage = message.toUpperCase();
                break;
            case "Whisper":
                transformedMessage = message.toLowerCase();
                break;
            default:
                transformedMessage = message;
        }
        return transformedMessage;
    }
}

