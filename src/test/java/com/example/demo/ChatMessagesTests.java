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
    private SignupPage signup;
    private LoginPage login;
    private ChatPage chatPage;
    private String username = "username";
    private String password = "password";
    private String messageText1 = "hello";
    private String messageType1 = "Shout";

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
    }

    @BeforeEach
    public void beforeEach() {
        driver.get("http://localhost:" + port + "/login");
        signup = new SignupPage(driver);
        login = new LoginPage(driver);
        chatPage = new ChatPage(driver);
    }

    @Test
    public void testSignup() {
        login.goToSignup();
        WebDriverWait waitForSignup = new WebDriverWait(driver, 10);
        waitForSignup.until(webDriver -> webDriver.findElement(By.cssSelector("form[action=\"/signup\"]")));
        signup.signup(username, password);
        signup.goToLogin();
        WebDriverWait waitForLogin = new WebDriverWait(driver, 10);
        waitForLogin.until(webDriver -> webDriver.findElement(By.cssSelector("form[action=\"/login\"]")));
        login.login(username, password);
        WebDriverWait waitForChat = new WebDriverWait(driver, 10);
        waitForChat.until(webDriver -> webDriver.findElement(By.cssSelector("form[action=\"/chat\"]")));
        chatPage.addChatMessage(messageText1, messageType1);
        assert(chatPage.getChatMessagesSection().getText().equals(username+": "+ messageText1.toUpperCase()));
    }
}
