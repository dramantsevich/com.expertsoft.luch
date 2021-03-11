package page;

import org.openqa.selenium.WebDriver;

public abstract class AbstractPageWithStaticURL extends AbstarctPage{
    protected AbstractPageWithStaticURL(WebDriver driver) {
        super(driver);
    }

    protected abstract AbstarctPage openPage();
}
