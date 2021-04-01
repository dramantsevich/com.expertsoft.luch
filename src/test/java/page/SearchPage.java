package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage extends AbstractPageWithStaticURL{
    private final String BASE_URL = "https://luch.by/en/search/";
    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//input[@class='input__default']")
    private WebElement searchInput;

    @FindBy(xpath = "//a[@class='name']")
    private WebElement linkToProduct;

    @FindBy(xpath = "//input[@class='button _transparent button-search']")
    private WebElement submitButton;

    public SearchPage() throws Throwable {
        super();
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public SearchPage openPage() {
        driver.navigate().to(BASE_URL);
        logger.info("Search page opened");
        return this;
    }

    public SearchPage enterSearchInput(String string) throws Throwable {
        sendkeysUsingJavascipt(searchInput, string);
        submitButton.click();

        return this;
    }

    public ProductPage goToProduct() throws Throwable {
        waitforVisibility(linkToProduct);
        linkToProduct.click();

        return new ProductPage();
    }
}
