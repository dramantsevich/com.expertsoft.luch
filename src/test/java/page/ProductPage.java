package page;

import model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends AbstractPageWithParametrizedURL{
    private final Logger logger = LogManager.getRootLogger();

    By header = By.xpath("//h1");
    By addProductToOrderButton = By.xpath("//button[@class='button _big button_add']");
    By checkoutButton = By.xpath("//span[contains(text(),'Checkout')]");
    By goToCartPageButton = By.xpath("//a[@class='btn btn-link product-item-detail-buy-button button _big']");

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, driver);
    }

    @Override
    public ProductPage openPage(String urlPart) {
        driver.get("https://luch.by" + urlPart);
        logger.info("Product page: "+ urlPart +" opened");
        return this;
    }

    public boolean isProductHeaderDisplyed() throws Throwable {
        waitforVisibility(driver.findElement(header));
        logger.info("Product header is displayed");
        return driver.findElement(By.xpath("//h1")).isDisplayed();
    }

    public ProductPage addToCart(Product product){
        driver.findElement(addProductToOrderButton).click();
        product.setCount(product.getCount() + 1);
        logger.info("Added product to order, count of product: "+ product.getCount() +"");
        return this;
    }

    public CartPage goToCartPage() throws Throwable {
        waitForElementClickable(driver.findElement(checkoutButton));
        driver.findElement(goToCartPageButton).click();
        logger.info("Click to 'checkout' on product page");
        return new CartPage(driver);
    }
}
