package com.luma.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BrowserUtils;

public class CreateAccountPage {

    public CreateAccountPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#firstname")
    WebElement firstname;

    @FindBy(css = "#lastname")
    WebElement lastname;

    @FindBy(css = "#email_address")
    WebElement email;

    @FindBy(xpath = "//div[@id='password-strength-meter-container']//preceding-sibling::input")
    WebElement password;

    @FindBy(css = "input[name='password_confirmation']")
    WebElement passwordConfirmed;

    @FindBy(css = "button[title='Create an Account']")
    WebElement createAccountLink;

    @FindBy(xpath = "//div[.='Thank you for registering with Main Website Store.']")
    WebElement successMessage;



    public void scrollPage(WebDriver driver){

        BrowserUtils.scrollWithPoint(driver, firstname);

    }

    public void firstnameAndLastname(String firstname, String lastname){

        this.firstname.sendKeys(firstname);
        this.lastname.sendKeys(lastname);

    }

    public void emailAndPassword(String email, String password){
        this.email.sendKeys(email);
        this.password.sendKeys(password);
    }

    public void confirmPassword(String passwordConfirmed){
        this.passwordConfirmed.sendKeys(passwordConfirmed);
    }

    public void clickOnCreateAccount(){
        createAccountLink.click();
    }

    public String validateMessage(WebDriver driver){
        return BrowserUtils.getText(driver, this.successMessage);
    }


}
