package com.example.demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;

public class SeleniumTest {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/animal");
        submitAnimals(driver);
        Thread.sleep(3000);
        driver.quit();
    }

    private static void submitAnimals(WebDriver driver) throws InterruptedException {
        HashMap<String, String> animals = new HashMap<String, String>();
        animals.put("dog", "good");
        animals.put("cat", "cool");
        animals.put("horse", "high");
        animals.put("bird", "bad");
        animals.put("bat", "black");

        for (Map.Entry animalAdjectivePair : animals.entrySet()) {
            WebElement animalField = driver.findElement(By.id("animalText"));
            WebElement adjectiveField = driver.findElement(By.id("adjective"));
            WebElement submitField = driver.findElement(By.cssSelector("input[type='submit']"));
            String animal = (String) animalAdjectivePair.getKey();
            animalField.sendKeys(animal);
            String adjective = (String) animalAdjectivePair.getValue();
            adjectiveField.sendKeys(adjective);
            submitField.click();
            Thread.sleep(1000);
        }
    }

}
