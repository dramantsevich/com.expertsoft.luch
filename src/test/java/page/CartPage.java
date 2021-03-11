package page;

import model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tools.ant.taskdefs.condition.Or;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends AbstractPageWithStaticURL{
    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//a[@class=' button _big _uppercase']")
    private WebElement checkoutButton;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public CartPage openPage() {
        driver.get("https://luch.by/en/cart/");
        logger.info("Cart page opened");
        return this;
    }

    public boolean isProductContainInCart(Product product){
        boolean isProductContainInCart = driver.findElement(By.xpath("//h2[@class='bx_ordercart_itemtitle']/a[@href='" + product.getProductURL() + "']")).isEnabled();
        logger.info("Is product: "+ product.getProductURL() +" contain in cart: "+ isProductContainInCart +"");
        return isProductContainInCart;
    }

    public OrderPage goToOrderPage() throws Throwable {
        waitForElementClickable(checkoutButton);
        checkoutButton.click();
        logger.info("Checkout button clicked");
        return new OrderPage(driver);
    }
}
