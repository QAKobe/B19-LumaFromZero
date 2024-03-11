package com.luma.step_definitions;

import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import utils.BrowserUtils;
import utils.DriverHelper;

public class LumaHook {

    public static WebDriver driver = DriverHelper.getDriver();

    @Before
    public void setDriver() {
        BrowserUtils.getURL(driver, "lumaURL");
    }


}
