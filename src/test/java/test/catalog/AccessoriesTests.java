package test.catalog;

import model.Product;
import org.testng.annotations.Test;
import page.catalog.CatalogAccessoriesPage;
import page.CatalogPage;
import page.ProductPage;
import test.CommonConditions;
import util.DataProvider;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AccessoriesTests extends CommonConditions {
    @Test
    public void CheckSortFirstPopular() throws Throwable{
        CatalogAccessoriesPage accessoriesPage = new CatalogAccessoriesPage()
                .openPage();

        CatalogPage catalogPage = new CatalogAccessoriesPage()
                .clickSortButton()
                .clickSortByName("First popular");

        List<Product> productsList = accessoriesPage.getListProducts();
    }

    @Test(dataProvider = "getAccessoriesTypeData")
    public void checkCorrectAccessroiesProductType(String accessoriesProductType) throws Throwable {
        CatalogPage catalogPage = new CatalogAccessoriesPage()
                .openPage()
                .clickFilterByName(accessoriesProductType);

        ProductPage productPage = catalogPage.clickMoreInfoOnWatchesByIndex(1);

        assertThat(productPage.getProductType().getText()).contains(accessoriesProductType);
    }

    @Test(dataProvider = "getAccessoriesColorData")
    public void checkCheckCorrectAccessoriesColour(String accessoriesColour) throws Throwable {
        CatalogPage catalogPage = new CatalogAccessoriesPage()
                .openPage()
                .clickFilterByName(accessoriesColour);

        ProductPage productPage = catalogPage.clickMoreInfoOnWatchesByIndex(1);

        assertThat(productPage.getProductColour().getText()).contains(accessoriesColour);
    }
    @org.testng.annotations.DataProvider
    public Object[] getAccessoriesTypeData() throws IOException {
        return DataProvider.read("accessoriesProductType");
    }

    @org.testng.annotations.DataProvider
    public Object [] getAccessoriesColorData() throws IOException {
        return DataProvider.read("accessoriesProductColor");
    }
}
