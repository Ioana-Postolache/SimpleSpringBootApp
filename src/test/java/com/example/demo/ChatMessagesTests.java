package com.example.demo;

import com.example.demo.Pages.ChatPage;
import com.example.demo.Pages.LoginPage;
import com.example.demo.Pages.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ChatMessagesTests {

    @LocalServerPort
    private Integer port;

    private static WebDriver driver;
    private String baseURL;
    private SignupPage signup;
    private LoginPage login;
    private ChatPage chatPage;


    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
        driver = null;
    }

    @BeforeEach
    public void beforeEach() {
        baseURL = "http://localhost:" + port;
        driver.get(baseURL + "/login");
        signup = new SignupPage(driver);
        login = new LoginPage(driver);
        chatPage = new ChatPage(driver);
    }

    @Test
    public void testSignup() throws InterruptedException {
        String username = "username";
        String password = "password";
        String messageText1 = "hello";
        String messageType1 = "Shout";
        login.goToSignup();
        WebDriverWait waitForSignup = new WebDriverWait(driver, 10);
        waitForSignup.until(webDriver -> webDriver.findElement(By.cssSelector("form[action=\"/signup\"]")));
        signup.signup(username, password);
        driver.get(baseURL + "/login");
        login.login(username, password);
        WebDriverWait waitForChat = new WebDriverWait(driver, 10);
        waitForChat.until(webDriver -> webDriver.findElement(By.cssSelector("form[action=\"/chat\"]")));
        chatPage.addChatMessage(messageText1, messageType1);
        WebDriverWait waitForChatMessages = new WebDriverWait(driver, 10);
        waitForChatMessages.until(webDriver -> webDriver.findElement(By.id("chat-messages")));
        assert (chatPage.getChatMessagesSection().getText().equals(username + ": " + messageText1.toUpperCase()));
    }
}
