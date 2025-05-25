package com.mycompany.app;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task3 {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        try {
            String url = "https://api.open-meteo.com/v1/forecast"
                       + "?latitude=56&longitude=44"
                       + "&hourly=temperature_2m,rain"
                       + "&timezone=Europe/Moscow"
                       + "&forecast_days=1"
                       + "&wind_speed_unit=ms";
            webDriver.get(url);

            WebElement pre = webDriver.findElement(By.tagName("pre"));
            String jsonStr = pre.getText();

            JSONParser parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(jsonStr);
            JSONObject hourly = (JSONObject) root.get("hourly");

            JSONArray times = (JSONArray) hourly.get("time");
            JSONArray temps = (JSONArray) hourly.get("temperature_2m");
            JSONArray rains = (JSONArray) hourly.get("rain");

            System.out.printf("%-3s %-20s %-12s %-10s%n", "№", "Дата/время", "Температура", "Осадки (мм)");
            for (int i = 0; i < times.size(); i++) {
                String time = (String) times.get(i);
                Object temp = temps.get(i);
                Object rain = rains.get(i);
                System.out.printf("%-3d %-20s %-12s %-10s%n", i + 1, time, temp, rain);
            }
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        } finally {
            webDriver.quit();
        }
    }
}
