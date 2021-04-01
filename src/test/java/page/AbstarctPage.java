package page;

import driver.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstarctPage {
    protected WebDriver driver;
    protected static final int WAIT_TIMEOUT_SECONDS = 10;

    protected AbstarctPage() throws Throwable {
        driver = DriverSingleton.getDriver();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public static WebElement waitforVisibility(WebElement element) throws Throwable {
        new WebDriverWait(DriverSingleton.getDriver(), WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public static WebElement waitForElementClickable(WebElement element) throws Throwable {
        new WebDriverWait(DriverSingleton.getDriver(), WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }

    public static boolean waitWebElementVisibilityInDOM(By by) throws Throwable {
        return (!new WebDriverWait(DriverSingleton.getDriver(), WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.invisibilityOfElementLocated(by)));
    }

    public static WebElement waitWebElementLocatedBy(By by) throws Throwable {
        return (new WebDriverWait(DriverSingleton.getDriver(), WAIT_TIMEOUT_SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void sendkeysUsingJavascipt(WebElement element, String value) throws Throwable {
        waitforVisibility(element);
        // TODO Auto-generated method stub
        JavascriptExecutor executor = (JavascriptExecutor)DriverSingleton.getDriver();
        executor.executeScript("arguments[0].setAttribute('value', '" + value +"')", element);
    }
}
