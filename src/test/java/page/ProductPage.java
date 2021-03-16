package page;

import model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends AbstractPageWithParametrizedURL{
    private final Logger logger = LogManager.getRootLogger();

    By addProductToOrderButton = By.xpath("//button[@class='button _big button_add']");
    By checkoutButton = By.xpath("//span[contains(text(),'Checkout')]");
    By goToCartPageButton = By.xpath("//a[@class='btn btn-link product-item-detail-buy-button button _big']");

    public ProductPage() throws Throwable {
        super();
        PageFactory.initElements(this.driver, driver);
    }

    @Override
    public ProductPage openPage(String urlPart) {
        driver.get("https://luch.by" + urlPart);
        logger.info("Product page: "+ urlPart +" opened");
        return this;
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
        return new CartPage();
    }
}
