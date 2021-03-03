package page;

import driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstarctPage {
    protected WebDriver driver;

    protected abstract AbstarctPage openPage();
    protected static final int WAIT_TIMEOUT_SECONDS = 10;

    protected AbstarctPage(WebDriver driver){
        this.driver = driver;
    }

    public static WebElement waitforVisibility(WebElement element) throws Throwable {
        new WebDriverWait(DriverSingleton.getDriver(), WAIT_TIMEOUT_SECONDS)
                    .until(ExpectedConditions.visibilityOf(element));
        return element;
    }
}
