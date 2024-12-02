package com.example.wsbp.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.servlet.http.HttpSession;
import org.springframework.mock.web.MockHttpSession;


import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SignPageTest {

    @Test
    @DisplayName("テスト")
    public void test(){
        WebDriver driver = new ChromeDriver();

        System.setProperty("webdriver.chrome.driver", "chromedriver_32/chromedriver");

        String baseUrl = "http://localhost:8080/Signed";

        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.quit();

    }


}
