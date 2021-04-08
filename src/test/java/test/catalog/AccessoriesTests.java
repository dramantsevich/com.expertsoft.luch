package test.catalog;

import com.opencsv.exceptions.CsvException;
import model.Product;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.catalog.CatalogAccessoriesPage;
import page.CatalogPage;
import page.ProductPage;
import test.CommonConditions;
import util.CustomDataProvider;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AccessoriesTests extends CommonConditions {
    @Test(dataProvider = "TypeAccessoriesProvider")
    public void checkCorrectAccessroiesProductType(String accessoriesProductType) throws Throwable {
        CatalogPage catalogPage = new CatalogAccessoriesPage()
                .openPage()
                .clickFilterByName(accessoriesProductType);

        ProductPage productPage = catalogPage.clickMoreInfoOnWatchesByIndex(1);

        assertThat(productPage.getProductType().getText()).contains(accessoriesProductType);
    }

    @Test(dataProvider = "ColourAccessoriesProvider")
    public void checkCheckCorrectAccessoriesColour(String accessoriesColour) throws Throwable {
        CatalogPage catalogPage = new CatalogAccessoriesPage()
                .openPage()
                .clickFilterByName(accessoriesColour);

        ProductPage productPage = catalogPage.clickMoreInfoOnWatchesByIndex(1);

        assertThat(productPage.getProductColour().getText()).contains(accessoriesColour);
    }

    @Test
    public void CheckSortFirstPopular() throws Throwable{
        CatalogAccessoriesPage accessoriesPage = new CatalogAccessoriesPage()
                .openPage();

        CatalogPage catalogPage = new CatalogAccessoriesPage()
                .clickSortButton()
                .clickSortByName("First popular");

        List<Product> productsList = accessoriesPage.getListProducts();
    }

    @DataProvider(name = "TypeAccessoriesProvider")
    public Object [] getTypeData() throws IOException, CsvException {
        return CustomDataProvider.readCsv("accessories/productType.csv");
    }

    @DataProvider(name = "ColourAccessoriesProvider")
    public Object [] getColourData() throws IOException, CsvException {
        return CustomDataProvider.readCsv("accessories/productColour.csv");
    }
}
