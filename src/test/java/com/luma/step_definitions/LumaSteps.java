package com.luma.step_definitions;

import com.luma.pages.CreateAccountPage;
import com.luma.pages.LumaMain;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class LumaSteps {

    WebDriver driver = LumaHook.driver;

    LumaMain main = new LumaMain(driver);

    CreateAccountPage page = new CreateAccountPage(driver);

    @Given("user is main page and clicks on create account link")
    public void user_is_main_page_and_clicks_on_create_account_link() {
        main.clickOnCreateAccount();
    }

    @Then("user scrolls down the page")
    public void user_scrolls_down_the_page() {
        page.scrollPage(driver);
    }

    @Then("user enters firstname and lastname as")
    public void user_enters_firstname_and_lastname_as(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> clientInfo = dataTable.asMap();
        page.firstnameAndLastname(clientInfo.get("firstname"), clientInfo.get("lastname"));
    }

    @Then("user enters email and password as")
    public void user_enters_email_and_password_as(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> emailPass = dataTable.asMap();
        page.emailAndPassword(emailPass.get("email"), emailPass.get("password"));
    }

    @Then("user confirms password as previously entered")
    public void user_confirms_password_as_previously_entered(io.cucumber.datatable.DataTable dataTable) {
        Map<String, String> confirmPassword = dataTable.asMap();
        page.confirmPassword(confirmPassword.get("confirmPassword"));
    }

    @Then("user clicks on create account link")
    public void user_clicks_on_create_account_link() {
        page.clickOnCreateAccount();
    }

    @Then("user validates success message")
    public void user_validates_success_message(io.cucumber.datatable.DataTable dataTable) {
       Map<String, String> message = dataTable.asMap();
        Assert.assertEquals(message.get("successMessage"), page.validateMessage(driver));
        System.out.println(page.validateMessage(driver));

    }

}
