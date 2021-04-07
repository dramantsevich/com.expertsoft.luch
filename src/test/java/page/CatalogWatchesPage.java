package page;

import model.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CatalogWatchesPage extends CatalogPage{
    private final String BASE_URL = "https://luch.by/en/watches/";
    private final String HIGHEST_FIRST_URL = "https://luch.by/en/watches/?sort=PRICE&order=asc";

    @FindBy(xpath = "//div[@class='name' and text()='Type']/div[@class='b-dropper ']")
    private WebElement typeFilterDropperButton;

    @FindBy(xpath = "//div[@class='name' and text()='Type']/following-sibling::div/div[@class='wrapper']")
    private WebElement typeFilterWrapper;

    public CatalogWatchesPage() throws Throwable {
        super();
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public CatalogWatchesPage openPage() {
        driver.navigate().to(BASE_URL);
        logger.info("Main page opened");
        return this;
    }

    public CatalogWatchesPage openHighestFirstPage(){
        driver.navigate().to(HIGHEST_FIRST_URL);
        logger.info("Highest first page opened");
        return this;
    }

    public CatalogWatchesPage dropTypeFilter() throws Throwable {
        typeFilterDropperButton.click();
        waitforVisibility(typeFilterWrapper);
        logger.info("Type filter dropper clicked");
        return this;
    }

    public ProductPage clickMoreInfoOnWatchesByIndex(int index) throws Throwable {
        waitforVisibility(driver.findElement(By.xpath("//div[@class='item']["+ index +"]//span[@class='more-btn']")));
        driver.findElement(By.xpath("//div[@class='item']["+ index +"]//span[@class='more-btn']"))
                .click();
        logger.info("Click to 'More info' button");
        return new ProductPage();
    }

    public List<Product> getListProducts() throws Throwable {
        List<Product> productsList = createListProducts();
        int index = 0;

        for(Product product : productsList){
            setProductName(product, index);
            setProductArticle(product, index);
            setProductPrice(product, index);

            index++;
        }

        return productsList;
    }
}
