package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URI;

public class DriverSingleton {
    private static WebDriver driver;

    private DriverSingleton(){ }

    public static WebDriver getDriver() throws Throwable {
        if(null == driver){
            switch (System.getProperty("browser")){
                case "chrome-selenoid":{
                    DesiredCapabilities capabilities = new DesiredCapabilities();
                    capabilities.setCapability("browserName", "chrome");
                    capabilities.setCapability("browserVersion", "88.0");
                    capabilities.setCapability("enableVNC", true);
                    capabilities.setCapability("enableVideo", true);
                    driver = new RemoteWebDriver(
                            URI.create("http://104.248.27.208:4444/wd/hub").toURL(),
                            capabilities);
                    break;
                }
                case "firefox": {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                }
                case "chrome":
                default:{
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                }
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void deleteAllCookies(){
        driver.manage().deleteAllCookies();
    }

    public static void closeDriver(){
        driver.quit();
        driver = null;
    }
}
