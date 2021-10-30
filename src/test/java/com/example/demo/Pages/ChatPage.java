package com.example.demo.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ChatPage {
    @FindBy(id = "messageText")
    private WebElement messageTextField;

    @FindBy(id = "messageType")
    private WebElement messageTypeSelect;

    @FindBy(css = "input[type='submit']")
    private WebElement submitButton;

    @FindBy(id = "chat-messages")
    private WebElement chatMessagesSection;

    public ChatPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void addChatMessage(String messageText, String messagetype) {
        messageTextField.sendKeys(messageText);
        Select messageTypeSelectField = new Select(messageTypeSelect);
        messageTypeSelectField.selectByValue(messagetype);
        submitButton.click();
    }

    public WebElement getChatMessagesSection() {
        return chatMessagesSection;
    }
}
