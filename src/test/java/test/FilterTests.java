package test;

import static org.assertj.core.api.Assertions.*;

import org.testng.annotations.Test;
import page.CatalogPage;
import page.ProductPage;
import util.CustomDataProvider;

public class FilterTests extends CommonConditions{
    @Test(dataProvider = "TypeFilterProvider", dataProviderClass = CustomDataProvider.class)
    public void checkCorrectTypeFilter(String typeName) throws Throwable {
        CatalogPage catalogPage = new CatalogPage()
                .openPage()
                .dropTypeFilter()
                .clickTypeFilterByName(typeName)
                .waitForTagsList();

        ProductPage productPage = catalogPage.clickMoreInfoOnWatchesByIndex(1);

        assertThat(productPage.getGenderType().getText()).isEqualTo(typeName);
    }

    @Test(dataProvider = "MovementFilterProvider", dataProviderClass = CustomDataProvider.class)
    public void checkCorrectMovementFilter(String movementName) throws Throwable {
        CatalogPage catalogPage = new CatalogPage()
                .openPage()
                .clickMovementFilterByName(movementName);

        ProductPage productPage = catalogPage.clickMoreInfoOnWatchesByIndex(1);

        assertThat(productPage.getMovementDescription().getText()).isEqualTo(movementName);
    }
}
