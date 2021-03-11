package page;

import org.openqa.selenium.WebDriver;

public abstract class AbstractPageWithParametrizedURL extends AbstarctPage{

    protected AbstractPageWithParametrizedURL(WebDriver driver) {
        super(driver);
    }

    protected abstract AbstarctPage openPage(String urlPart);
}
