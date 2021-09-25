package com.cybertek.tests.homeworks;

import com.cybertek.tests.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class eu2_9to12 {

    WebDriver driver;

    @BeforeMethod
    void setupBeforeMethod(){
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod
    void tearDownMethod(){
        driver.quit();
    }

    /*
Step 1. Go to “https://practice-cybertekschool.herokuapp.com”
Step 2. And click on “Status Codes”.
Step 3. Then click on “200”.
Step 4. Verify that following message is displayed: “This page returned a 200 status code”
     */
    @Test
    void testCase9(){
        String statCode = "200";
        Assert.assertTrue(getMessageText(statCode).contains("This page returned a " + statCode + " status code"));
    }

    /*
Step 1. Go to “https://practice-cybertekschool.herokuapp.com”
Step 2. And click on “Status Codes”.
Step 3. Then click on “301”.
Step 4. Verify that following message is displayed: “This page returned a 301 status code”
     */
    @Test
    void testCase10(){
        String statCode = "301";
        Assert.assertTrue(getMessageText(statCode).contains("This page returned a " + statCode + " status code"));
    }

    /*
Step 1. Go to “https://practice-cybertekschool.herokuapp.com”
Step 3. And click on “Status Codes”.
Step 4. Then click on “404”.
Step 5. Verify that following message is displayed: “This page returned a 404 status code”
     */
    @Test
    void testCase11(){
        String statCode = "404";
        Assert.assertTrue(getMessageText(statCode).contains("This page returned a " + statCode + " status code"));
    }

    /*
Step 1. Go to “https://practice-cybertekschool.herokuapp.com”
Step 3. And click on “Status Codes”.
Step 4. Then click on “500”.
Step 5. Verify that following message is displayed: “This page returned a 500 status code”
     */
    @Test
    void testCase12(){
        String statCode = "500";
        Assert.assertTrue(getMessageText(statCode).contains("This page returned a " + statCode + " status code"));
    }

    String getMessageText(String statusCode){
        driver.get("https://practice-cybertekschool.herokuapp.com");

        driver.findElement(By.linkText("Status Codes")).click();

        driver.findElement(By.linkText(statusCode)).click();

        return driver.findElement(By.xpath("//h3/following-sibling::p")).getText();
    }

}
