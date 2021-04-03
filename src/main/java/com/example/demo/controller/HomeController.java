package com.example.demo.controller;

import com.example.demo.model.MessageForm;
import com.example.demo.service.HarderHomeMessagesService;
import com.example.demo.service.MessageListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/animal")
public class HomeController {
    private HarderHomeMessagesService messageListService;
    

    public HomeController(HarderHomeMessagesService messageListService) {
        this.messageListService = messageListService;
    }

    @GetMapping()
    public String lowFive(MessageForm messageForm, Model model) {
        String name = messageForm.getAnimalName() == null ? "user": messageForm.getAdjective() + " " + messageForm.getAnimalName();
        messageListService.addMessage("Welcome, " + name);
        model.addAttribute("greetings", messageListService.getMessages());
        return "harder-home";
    }

    @PostMapping()
    public String highFive(MessageForm messageForm, Model model) {
        messageListService.addMessage("We shall now study the " + messageForm.getAdjective() + " " + messageForm.getAnimalName() + " style.");
        messageForm.setAnimalName("");
        messageForm.setAdjective("");
        model.addAttribute("greetings", messageListService.getMessages());
        return "harder-home";
    }
}
