package test;

import static org.assertj.core.api.Assertions.*;

import com.opencsv.exceptions.CsvException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.ProductPage;
import page.SearchPage;
import util.CustomDataProvider;

import java.io.IOException;

public class SearchTest extends CommonConditions{
    @Test(dataProvider = "ProductArticleProvider")
    public void checkCorrectSearch(String article) throws Throwable {
        SearchPage searchPage = new SearchPage()
                .openPage()
                .enterSearchInput(article);

        ProductPage productPage = searchPage.goToProduct();

        assertThat(productPage.getCurrentUrl()).contains(article);
    }

    @DataProvider(name = "ProductArticleProvider")
    public Object [] getMovementData() throws IOException, CsvException {
        return CustomDataProvider.readCsv("productArticle.csv");
    }
}
