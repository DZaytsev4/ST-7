package com.mycompany.app;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task2 {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();

        try {
            webDriver.get("https://api.ipify.org/?format=json");
            WebElement elem = webDriver.findElement(By.tagName("pre"));

            String json_str = elem.getText();
            System.out.println("JSON-ответ: " + json_str);

            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(json_str);
            String ip = (String) obj.get("ip");

            System.out.println("Ваш IP-адрес: " + ip);

        } catch (Exception e) {
            System.out.println("Произошла ошибка:");
            System.out.println(e.toString());
        } finally {
            webDriver.quit();
        }
    }
}