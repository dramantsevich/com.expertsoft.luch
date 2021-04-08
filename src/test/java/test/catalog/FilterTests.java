package test.catalog;

import static org.assertj.core.api.Assertions.*;

import com.opencsv.exceptions.CsvException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.CatalogPage;
import page.catalog.CatalogWatchesPage;
import page.ProductPage;
import test.CommonConditions;
import util.CustomDataProvider;

import java.io.IOException;

public class FilterTests extends CommonConditions {
    @Test(dataProvider = "TypeFilterProvider")
    public void checkCorrectTypeFilter(String typeName) throws Throwable {
        CatalogPage catalogPage = new CatalogWatchesPage()
                .openPage()
                .dropTypeFilter()
                .clickFilterByName(typeName)
                .waitForTagsList();

        ProductPage productPage = catalogPage.clickMoreInfoOnWatchesByIndex(1);

        assertThat(productPage.getGenderType().getText()).contains(typeName);
    }

    @Test(dataProvider = "MovementFilterProvider")
    public void checkCorrectMovementFilter(String movementName) throws Throwable {
        CatalogPage catalogPage = new CatalogWatchesPage()
                .openPage()
                .clickFilterByName(movementName);

        ProductPage productPage = catalogPage.clickMoreInfoOnWatchesByIndex(1);

        assertThat(productPage.getMovementDescription().getText()).isEqualTo(movementName);
    }

    @DataProvider(name = "TypeFilterProvider")
    public Object [] getTypeData() throws IOException, CsvException {
        return CustomDataProvider.readCsv("typeFilter.csv");
    }

    @DataProvider(name = "MovementFilterProvider")
    public Object [] getMovementData() throws IOException, CsvException {
        return CustomDataProvider.readCsv("movementFilter.csv");
    }
}
