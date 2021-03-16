package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage extends AbstractPageWithParametrizedURL{
    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//div[@class='section-name _h3']")
    private WebElement orderCompeleteMessage;

    public PaymentPage() throws Throwable {
        super();
        PageFactory.initElements(this.driver, this);
    }

    @Override
    protected AbstarctPage openPage(String urlPart) {
        return null;
    }

    public WebElement getOrderCompeleteMessage() { return orderCompeleteMessage; }
}
