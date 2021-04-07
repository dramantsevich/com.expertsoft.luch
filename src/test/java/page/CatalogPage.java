package page;

import model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import service.ProductCreator;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CatalogPage extends AbstractPageWithStaticURL{
    protected final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//div[@class='button b-sort-by _js-b-mobile-sort-by']")
    private WebElement sortButton;

    @FindBy(xpath = "//div[@class='button b-sort-by _js-b-mobile-sort-by _active']")
    private WebElement sortButtonActive;

    @FindBy(xpath = "//div[@class='item']")
    private List<WebElement> itemList;

    @FindBy(xpath = "//a/div/div/div[@class='name']")
    private List<WebElement> itemNameList;

    @FindBy(xpath = "//a/div/div/div[@class='article']")
    private List<WebElement> itemArticleList;

    @FindBy(xpath = "//a/div/div/div/div/div[@class='price']/span")
    private List<WebElement> itemPriceList;

    @FindBy(xpath = "//div[@class='tags-list']")
    private WebElement tagList;

    public CatalogPage() throws Throwable {
        super();
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public CatalogPage openPage() {
        return this;
    }

    public CatalogPage clickFilterByName(String filterName) throws Throwable {
        waitForElementClickable(driver.findElement(By.xpath("//div[@class='input checkbox']/label[contains(text(),'"+ filterName +"')]")));

        driver.findElement(By.xpath("//div[@class='input checkbox']/label[contains(text(),'"+ filterName +"')]"))
                .click();
        Thread.sleep(1000);
        logger.info("ProductType filter "+ filterName +" clicked");

        return this;
    }

    public ProductPage clickMoreInfoOnWatchesByIndex(int index) throws Throwable {
        waitforVisibility(driver.findElement(By.xpath("//div[@class='row product-item-list-col-3'][1]//div[@class='item']["+ index +"]//span[@class='more-btn']")));
        driver.findElement(By.xpath("//div[@class='row product-item-list-col-3']["+ index +"]//div[@class='item']["+ index +"]//span[@class='more-btn']"))
                .click();
        logger.info("Click to 'More info' button");
        return new ProductPage();
    }

    public CatalogPage clickSortButton() throws Throwable {
        waitForElementClickable(sortButton);

        sortButton.click();

        return this;
    }

    public CatalogPage clickSortByName(String sortName) throws Throwable {
        waitforVisibility(sortButtonActive);
        waitForElementClickable(driver.findElement(By.xpath("//div[@class='sort']/a/span[contains(text(),'"+ sortName +"')]")));
        Thread.sleep(1000);

        driver.findElement(By.xpath("//div[@class='sort']/a/span[contains(text(),'"+ sortName +"')]"))
                .click();

        return this;
    }

    public CatalogPage waitForTagsList() throws Throwable {
        waitforVisibility(tagList);
        return this;
    }

    protected List<Product> createListProducts() throws Throwable {
        List<Product> productsList = new ArrayList<>();

        for(WebElement item : itemList){
            waitforVisibility(item);
            Product product = ProductCreator.productFromCatalogPage();

            productsList.add(product);
        }

        return productsList;
    }

    protected Product setProductName(Product product, int index){
        String name = itemNameList.get(index).getText();
        product.setName(name);

        return product;
    }

    protected Product setProductArticle(Product product, int index){
        String article = itemArticleList.get(index).getText();
        product.setArticle(Integer.parseInt(article));

        return product;
    }

    protected Product setProductPrice(Product product, int index){
        String price = itemPriceList.get(index).getText().replaceAll("\\s+","");

        Pattern pattern = Pattern.compile("[^mr][\\d]+");
        Matcher matcher = pattern.matcher(price);

        if(matcher.find()){
            int start = matcher.start();
            int end = matcher.end();

            int itemPrice = Integer.parseInt(price.substring(start, end).trim());
            product.setPrice(itemPrice);

            logger.info("Price of item: "+ itemPrice +"");
            return product;
        }
        else{
            return product;
        }
    }
}
