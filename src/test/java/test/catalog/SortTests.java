package test.catalog;

import static org.assertj.core.api.Assertions.*;

import model.Product;
import org.testng.annotations.Test;
import page.CatalogPage;
import page.catalog.CatalogWatchesPage;
import test.CommonConditions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortTests extends CommonConditions {
    @Test
    public void CheckSortLowestFirst() throws Throwable {
        CatalogWatchesPage catalogWatchesPage = new CatalogWatchesPage()
                .openPage();

        CatalogPage catalogPage = new CatalogWatchesPage()
                .clickSortButton()
                .clickSortByName("Price: lowest first");

        List<Product> productsList = catalogWatchesPage.getListProducts();
        List<Product> sortedList = new ArrayList<>(productsList);
        sortedList.sort(Comparator.comparing(Product::getPrice).reversed());

        assertThat(productsList).isEqualTo(sortedList);
    }

    @Test
    public void CheckSortHighestFirst() throws Throwable {
        CatalogWatchesPage catalogPage = new CatalogWatchesPage()
                .openHighestFirstPage();

        List<Product> productsList = catalogPage.getListProducts();
        List<Product> sortedList = new ArrayList<>(productsList);
        sortedList.sort(Comparator.comparing(Product::getPrice));

        assertThat(productsList).isEqualTo(sortedList);
    }

    @Test
    public void CheckSortFirstPopular() throws Throwable{
        CatalogWatchesPage catalogWatchesPage = new CatalogWatchesPage()
                .openPage();

        CatalogPage catalogPage = new CatalogWatchesPage()
                .openPage()
                .clickSortButton()
                .clickSortByName("First popular");

        List<Product> productsList = catalogWatchesPage.getListProducts();
    }
}
