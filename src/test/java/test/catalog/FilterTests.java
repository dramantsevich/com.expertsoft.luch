package test.catalog;

import static org.assertj.core.api.Assertions.*;

import org.testng.annotations.Test;
import page.CatalogPage;
import page.catalog.CatalogWatchesPage;
import page.ProductPage;
import test.CommonConditions;
import util.DataProvider;

import java.io.IOException;

public class FilterTests extends CommonConditions {
    @Test(dataProvider = "getTypeFilterData")
    public void checkCorrectTypeFilter(String typeName) throws Throwable {
        CatalogPage catalogPage = new CatalogWatchesPage()
                .openPage()
                .dropTypeFilter()
                .clickFilterByName(typeName)
                .waitForTagsList();

        ProductPage productPage = catalogPage.clickMoreInfoOnWatchesByIndex(1);

        assertThat(productPage.getGenderType().getText()).contains(typeName);
    }

    @Test(dataProvider = "getMovementFilterData")
    public void checkCorrectMovementFilter(String movementName) throws Throwable {
        CatalogPage catalogPage = new CatalogWatchesPage()
                .openPage()
                .clickFilterByName(movementName);

        ProductPage productPage = catalogPage.clickMoreInfoOnWatchesByIndex(1);

        assertThat(productPage.getMovementDescription().getText()).isEqualTo(movementName);
    }

    @org.testng.annotations.DataProvider
    public Object[] getTypeFilterData() throws IOException {
        return DataProvider.read("typeFilter");
    }

    @org.testng.annotations.DataProvider
    public Object [] getMovementFilterData() throws IOException {
        return DataProvider.read("movementFilter");
    }
}
