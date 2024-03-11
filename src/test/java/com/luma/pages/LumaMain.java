package com.luma.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LumaMain {
    public LumaMain(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//li[.='Create an Account']")
    List<WebElement> createAccount;

    public void clickOnCreateAccount(){
        for (int i = 0; i < 1; i++) {
            createAccount.get(i).click();
        }
    }
}
