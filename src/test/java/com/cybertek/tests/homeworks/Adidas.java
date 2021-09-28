package com.cybertek.tests.homeworks;

import com.cybertek.tests.utilities.WebDriverFactory;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Adidas {
    WebDriver driver;

    @BeforeMethod
    void setupBeforeMethod(){
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterMethod
    void tearDownMethod() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
    }

    private void addToChart(String product){
        driver.get("https://www.demoblaze.com/index.html");
        driver.findElement(By.linkText("Laptops")).click();
        driver.findElement(By.linkText(product)).click();
        driver.findElement(By.linkText("Add to cart")).click();
    }

    @Test
    void test() throws InterruptedException {

        addToChart("Sony vaio i5");

        new WebDriverWait(driver, 4)
                .ignoring(NoAlertPresentException.class)
                .until(ExpectedConditions.alertIsPresent());

        driver.switchTo().alert().accept();

        int sonyVaioPrice = Integer.parseInt(driver.findElement(By.cssSelector(".price-container")).getText().split(" ")[0].substring(1));

        addToChart("Dell i7 8gb");

        //Thread.sleep(2000);

        new WebDriverWait(driver, 4)
                .ignoring(NoAlertPresentException.class)
                .until(ExpectedConditions.alertIsPresent());

        driver.switchTo().alert().accept();

        driver.findElement(By.linkText("Cart")).click();

        String urlCart = driver.getCurrentUrl();

        driver.findElement(By.cssSelector("#tbodyid tr:nth-of-type(2) td:nth-of-type(4) a")).click();

        Thread.sleep(2000);

        driver.get(urlCart);

        driver.findElement(By.xpath("//button[.='Place Order']")).click();

        Faker faker = new Faker();

        WebElement txtName = driver.findElement(By.id("name"));

        txtName.sendKeys(faker.name().fullName());
        driver.findElement((By.id("country"))).sendKeys(faker.country().name());
        driver.findElement(By.id("city")).sendKeys(faker.address().cityName());
        driver.findElement(By.id("card")).sendKeys(faker.finance().creditCard());
        driver.findElement(By.id("month")).sendKeys(String.valueOf(faker.number().numberBetween(1, 12)));

        int year = Calendar.getInstance().get(Calendar.YEAR);

        driver.findElement(By.id("year")).sendKeys(String.valueOf(faker.number().numberBetween(year, year + 10)));

        driver.findElement(By.xpath("//button[.='Purchase']")).click();

        WebElement pPurchaseResult = driver.findElement(By.cssSelector(".lead.text-muted"));

        String[] resultParts = pPurchaseResult.getAttribute("innerHTML").split("<br>");
        int resultID = Integer.parseInt(resultParts[0].split(" ")[1]);
        int resultAmount = Integer.parseInt(resultParts[1].split(" ")[1]);

        Assert.assertEquals(resultAmount, sonyVaioPrice);

        driver.findElement(By.xpath("//button[.='OK']")).click();
    }
}
