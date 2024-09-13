package com.policyTestDemo.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class LoginPage {
    WebDriver driver;
    @FindBy(xpath = "//label[text()='User ID or Email']/preceding-sibling::input")
    WebElement input_userId;

    @FindBy(xpath = "//label[text()='Password']//preceding-sibling::input[1]")
    WebElement input_password;
    @FindBy(xpath = "//span[text()='Login']")
    WebElement btn_login;
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public void setUserName(String userName){
        this.input_userId.sendKeys(userName);
    }
    public void setPassword(String password){
        this.input_password.sendKeys(password);
    }
    public void clickLogin(){
        btn_login.click();
    }

}
