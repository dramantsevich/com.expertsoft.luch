package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage extends AbstractPageWithParametrizedURL{
    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//div[contains(text(),'Order complete')]")
    private WebElement orderCompeleteMessage;

    public PaymentPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    protected AbstarctPage openPage(String urlPart) {
        return null;
    }

    public boolean isOrderCompleteMessageContain() throws Throwable {
        waitforVisibility(orderCompeleteMessage);
        logger.info("Is order complete message contain on payment page");
        return orderCompeleteMessage.isDisplayed();
    }
}
