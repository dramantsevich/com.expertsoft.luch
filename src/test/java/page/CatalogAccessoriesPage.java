package page;

import model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CatalogAccessoriesPage extends CatalogPage{
    private final String BASE_URL = "https://luch.by/en/accessories/";
    private final Logger logger = LogManager.getRootLogger();

    public CatalogAccessoriesPage() throws Throwable {
        super();
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public CatalogAccessoriesPage openPage() {
        driver.navigate().to(BASE_URL);
        logger.info("Main page opened");
        return this;
    }

    public List<Product> getListProducts() throws Throwable {
        List<Product> productsList = createListProducts();
        int index = 0;

        for(Product product : productsList){
            setProductName(product, index);
            setProductPrice(product, index);

            index++;
        }

        return productsList;
    }
}
