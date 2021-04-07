package page;

import model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends AbstractPageWithParametrizedURL{
    private final Logger logger = LogManager.getRootLogger();

    By addProductToOrderButton = By.xpath("//button[@class='button _big button_add']");
    By checkoutButton = By.xpath("//span[contains(text(),'Checkout')]");
    By goToCartPageButton = By.xpath("//a[@class='btn btn-link product-item-detail-buy-button button _big']");
    By genderTypeDescription = By.xpath("//div[@class='prop-title' and contains(text(),'Type')]/following-sibling::div[@class='prop-value']/a");
    By movementDescription = By.xpath("//div[@class='prop-title' and contains(text(),'Movement')]/following-sibling::div[@class='prop-value']/a");
    By productPrice = By.xpath("//meta[@itemprop='price']");
    By productType = By.xpath("//div[@class='prop-title' and contains(text(),'Product type')]/following-sibling::div[@class='prop-value']/a");
    By productColour = By.xpath("//div[@class='prop-title' and contains(text(),'Colour')]/following-sibling::div[@class='prop-value']/a");

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
        int price = Integer.parseInt(driver.findElement(productPrice).getAttribute("content"));
        driver.findElement(addProductToOrderButton).click();
        product.setPrice(price);
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

    public WebElement getGenderType() throws Throwable {
        waitforVisibility(driver.findElement(genderTypeDescription));

        return driver.findElement(genderTypeDescription);
    }

    public WebElement getMovementDescription() throws Throwable {
        waitforVisibility(driver.findElement(movementDescription));

        return driver.findElement(movementDescription);
    }

    public WebElement getProductType() throws Throwable {
        waitforVisibility(driver.findElement(productType));

        return driver.findElement(productType);
    }

    public WebElement getProductColour() throws Throwable {
        waitforVisibility(driver.findElement(productColour));

        return driver.findElement(productColour);
    }
}
