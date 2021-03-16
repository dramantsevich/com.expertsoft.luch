package test;

import driver.DriverSingleton;
import org.testng.annotations.*;
import page.MainPage;
import util.TestListener;

@Listeners({TestListener.class})
public class CommonConditions {

    @BeforeMethod
    public void browserSetUp() throws Throwable {
        new MainPage().openPage();

        DriverSingleton.deleteAllCookies();
    }

    @AfterClass
    protected void quiteBrowserAfterTest() {
        DriverSingleton.closeDriver();
    }
}
