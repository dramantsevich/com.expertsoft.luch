package test;

import driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import util.TestListener;

@Listeners({TestListener.class})
public class CommonConditions {
    protected WebDriver driver;

    @BeforeMethod
    public void browserSetUp() throws Throwable {
        driver = DriverSingleton.getDriver();
    }

    @AfterMethod(alwaysRun = true)
    protected void browserTearDown() {
        DriverSingleton.deleteAllCookies();
    }

    @AfterTest
    protected void quiteBrowserAfterTest() {
        DriverSingleton.closeDriver();
    }
}
