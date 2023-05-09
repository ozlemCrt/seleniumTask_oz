package com.devskiller.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SeleniumExecutor implements Executor {

    private final WebDriver driver;

    public SeleniumExecutor(WebDriver driver) {
        this.driver = driver;
    }

    /// Page 1
    @Override
    public void SetLoginAndClickNext(String login) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.id("emailBox")).sendKeys(login);
        driver.findElement(By.xpath("//a/button[@class='buttonLogin']")).click();
    }

    /// Page 2
    @Override
    public String OpenCodePageAndReturnCode() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//a[text()='open page']")).click();
      String expectUrl="  http://localhost:63342/seleniumTaskIA/selenium-exercise/__files/code.html";
        Set<String> windowHandles = driver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            driver.switchTo().window(windowHandle);
            if(driver.getCurrentUrl().equals(expectUrl)){
                break;
            }
        }
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("code")));
       String text = driver.findElement(By.xpath("//input[@id='code']")).getAttribute("value");
       return text ;
    }

    @Override
    public void SetCodeAndClickNext(String code) {
      driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String expectUrl="http://localhost:8089/index.html";
        Set<String> windowHandles = driver.getWindowHandles();
        for (String windowHandle : windowHandles) {
            driver.switchTo().window(windowHandle);
            if(driver.getCurrentUrl().equals(expectUrl)){
                break;
            }}
        driver.findElement(By.id("codeBox")).sendKeys(code);
        driver.findElement(By.xpath("//button[@class='buttonLoginCode']")).click();

    }

    /// Page 3
    @Override
    public void FillMaskedPasswordAndClickLogin(String password) {
        driver.switchTo().frame(0);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#passwd_0")));
        for (int i = 0; i < password.length(); i++) {
            if(driver.findElement(By.cssSelector("#passwd_"+i)).isEnabled()){
                driver.findElement(By.cssSelector("#passwd_"+i)).sendKeys(password.substring(i,i+1));
            }}
        driver.findElement(By.xpath("//button[@type='button']")).click();
    }

    @Override
    public String GetLoggedInText() {
     return   driver.findElement(By.cssSelector("#loggedIn")).getText();
    }

}
