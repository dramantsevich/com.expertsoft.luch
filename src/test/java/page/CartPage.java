package page;

import model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CartPage extends AbstractPageWithStaticURL{
    private final String CART_URL = "https://luch.by/en/cart/";
    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//a[@class=' button _big _uppercase']")
    private WebElement checkoutButton;

    @FindBy(xpath = "//table[@id='basket_items']/tbody/tr")
    private List<WebElement> listProducts;

    @FindBy(xpath = "//a[@class='plus']")
    private WebElement plusOneMoreProductQuantity;

    @FindBy(xpath = "//div[@id='allSum_FORMATED']")
    private WebElement totalSum;

    @FindBy(xpath = "//p/font")
    private WebElement emptyCartMessage;

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

    public List<WebElement> getListProducts(){ return listProducts; }

    public CartPage addOneMoreProductQuantity(Product product) throws Throwable {
        waitForElementClickable(driver.findElement(
                By.xpath("//td[@class='item td-name']//a[@href='" + product.getProductURL() + "']/ancestor::td/following-sibling::td[@class='custom td-count']//a[@class='plus']")));
        driver.findElement(
                By.xpath("//td[@class='item td-name']//a[@href='" + product.getProductURL() + "']/ancestor::td/following-sibling::td[@class='custom td-count']//a[@class='plus']"))
                .click();

        logger.info("Add one more product quantity");
        return this;
    }

    public int getTotalSum() throws Throwable {
        Thread.sleep(500);
        String price = totalSum.getText().replaceAll("\\s+","");

        Pattern pattern = Pattern.compile("^[\\d]*");
        Matcher matcher = pattern.matcher(price);

        if(matcher.find()){
            int start = matcher.start();
            int end = matcher.end();

            int totalPrice = Integer.parseInt(price.substring(start, end).trim());

            logger.info("Total price of item: "+ totalPrice +"");
            return totalPrice;
        }
        else{
            return 0;
        }
    }

    public int getQuantityCountByProduct(Product product){
        int quantityCount = Integer.parseInt(driver.findElement(
                By.xpath("//td[@class='item td-name']//a[@href='" + product.getProductURL() + "']/ancestor::td/following-sibling::td[@class='custom td-count']//input[@type='hidden']"))
                .getAttribute("value"));
        logger.info("Quantity count: " + quantityCount);
        return quantityCount;
    }

    public CartPage clickDeleteButtonByProduct(Product product){
        driver.findElement(
                By.xpath("//td[@class='item td-name']//a[@href='" + product.getProductURL() + "']/ancestor::td/following-sibling::td[@class='custom td-count']/a[@class='delete__link']"))
                .click();
        logger.info("Product deleted:" + product.getProductURL());
        return this;
    }

    public WebElement getEmptyCartMessage(){ return emptyCartMessage; }
}
