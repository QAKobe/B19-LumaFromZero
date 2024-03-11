package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

//Singleton Pattern Design(Centralize your driver into one spot)
public class DriverHelper {

    //private variable
    private static WebDriver driver;


    //private constructor
    private DriverHelper(){}// you can make the constructor private since you do not want to
    // create an object from this class. One of the great ways of Encapsulation.

    //
    public static WebDriver getDriver(){
        if (driver==null || ((RemoteWebDriver)driver).getSessionId()==null){
           // String browser = "chrome";
            switch (ConfigReader.readProperty("browser")){
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "firefox":

                    driver = new FirefoxDriver();
                    break;
                default:
                    driver = new ChromeDriver();
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
        return driver;
    }
}
