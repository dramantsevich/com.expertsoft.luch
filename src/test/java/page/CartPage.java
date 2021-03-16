package page;

import model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends AbstractPageWithStaticURL{
    private final String CART_URL = "https://luch.by/en/cart/";
    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//a[@class=' button _big _uppercase']")
    private WebElement checkoutButton;

    public CartPage() throws Throwable {
        super();
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public CartPage openPage() {
        driver.get(CART_URL);
        logger.info("Cart page opened");
        return this;
    }

    public WebElement getProductInCartByUrl(Product product){
        return driver.findElement(By.xpath("//h2[@class='bx_ordercart_itemtitle']/a[@href='" + product.getProductURL() + "']"));
    }

    public OrderPage goToOrderPage() throws Throwable {
        waitForElementClickable(checkoutButton);
        checkoutButton.click();
        logger.info("Checkout button clicked");
        return new OrderPage();
    }
}
