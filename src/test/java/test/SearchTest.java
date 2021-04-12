package test;

import static org.assertj.core.api.Assertions.*;

import org.testng.annotations.Test;
import page.ProductPage;
import page.SearchPage;
import util.DataProvider;

import java.io.IOException;

public class SearchTest extends CommonConditions{
    @Test(dataProvider = "getProductArticle")
    public void checkCorrectSearch(String article) throws Throwable {
        SearchPage searchPage = new SearchPage()
                .openPage()
                .enterSearchInput(article);

        ProductPage productPage = searchPage.goToProduct();

        assertThat(productPage.getCurrentUrl()).contains(article);
    }

    @org.testng.annotations.DataProvider
    public Object [] getProductArticle() throws IOException {
        return DataProvider.read("productArticle");
    }
}
