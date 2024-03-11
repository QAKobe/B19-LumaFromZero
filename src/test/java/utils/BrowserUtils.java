package utils;

import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class BrowserUtils {

    public static void selectBy(WebElement location, String value, String methodName) {

        Select select = new Select(location);

        switch (methodName) {
            case "visibleText":
                select.selectByVisibleText(value);
                break;
            case "value":
                select.selectByValue(value);
                break;
            case "index":
                select.selectByIndex(Integer.parseInt(value));
                break;
            default:
                System.out.println("Method name is not available, use text, value or index");
        }
    }

    public static void getURL(WebDriver driver, String url){
        driver.get(ConfigReader.readProperty(url));
    }

    public static String getText(WebElement element) {
        return element.getText().trim();
    }

    public static String getText(WebDriver driver, WebElement element) {
        // Wait for the element to be visible and have text
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(element),
                ExpectedConditions.textToBePresentInElement(element, "")
        ));

        // Return the trimmed text
        return element.getText().trim();
    }

    public static String getTitle(WebDriver driver){
        return driver.getTitle();
    }

    public static List<WebElement> getAllOptions(WebElement element) {
        Select select = new Select(element);
        List<WebElement> allOptions = select.getOptions();

        return allOptions;
    }

    public static void getScreenShot(WebDriver driver, String packageName) {

        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String location=System.getProperty("user.dir")+"/src/main/screenshots " + packageName;
        try {
            FileUtils.copyFile(file, new File(location + System.currentTimeMillis()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }



   public static void getScreenShotFixed(WebDriver driver, String packageName)

    {
        // This has been covered
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String location = System.getProperty("user.dir") + "/src/main/screenshots/";

        // Create the screenshots directory if it doesn't exist
        File directory = new File(location);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        try {
            FileUtils.copyFile(file, new File(location + System.currentTimeMillis() + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void clickWithJS(WebDriver driver, WebElement element) {

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(element));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", element);
    }

    public static String getTitleWithJS(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript("return document.title").toString();
    }

    public static void scrollViewWithJS(WebDriver driver, WebElement element) {

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(element));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true)", element);
    }

    public static void switchByTitle(WebDriver driver, String title) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.titleIs(title));
        Set<String> allPagesIds = driver.getWindowHandles();
        for (String id : allPagesIds) {
            driver.switchTo().window(id);
            if (driver.getTitle().contains(title)) {
                break;
            }

        }

    }

    public static void alertAccept(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public static void alertDismiss(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    public static void alertSendKeys(WebDriver driver, String keys){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(keys);
    }

    public static String getAlertText(WebDriver driver){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    public static void scrollWithPoint(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(element));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Point point = element.getLocation();
        int xCoordinate = point.getX();
        int yCoordinate = point.getY();
        js.executeScript("window.scrollTo(" + xCoordinate + "," + yCoordinate + ")");
    }


    public static void moveToElement(WebDriver driver, WebElement target){

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(target));
        Actions actions = new Actions(driver);
        actions.moveToElement(target).build().perform();


    }

    public static void scrollByAmount(WebDriver driver, int x, int y){

        Actions actions = new Actions(driver);
        actions.scrollByAmount(x, y).build().perform();

    }

    public static void takeScreenShot(Scenario scenario, WebDriver driver) {

        Date currentDate = new Date();
        String screenShotFileName = currentDate.toString().replace(":", "-");
        if (scenario.isFailed()) {
            File screenShotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(screenShotFile, new File("src/test/java/screenshot/" + screenShotFileName + ".png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

   






}
