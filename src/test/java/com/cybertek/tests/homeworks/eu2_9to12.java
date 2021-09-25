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
        driver.get("https://practice-cybertekschool.herokuapp.com");

        driver.findElement(By.linkText("Status Codes")).click();

        driver.findElement(By.linkText("200")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//h3/following-sibling::p")).getText().contains("This page returned a 200 status code"));
    }

    /*
Step 1. Go to “https://practice-cybertekschool.herokuapp.com”
Step 2. And click on “Status Codes”.
Step 3. Then click on “301”.
Step 4. Verify that following message is displayed: “This page returned a 301 status code”
     */
    @Test
    void testCase10(){
        driver.get("https://practice-cybertekschool.herokuapp.com");

        driver.findElement(By.linkText("Status Codes")).click();

        driver.findElement(By.linkText("301")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//h3/following-sibling::p")).getText().contains("This page returned a 301 status code"));
    }

    void repeatedActions(){

    }

}
