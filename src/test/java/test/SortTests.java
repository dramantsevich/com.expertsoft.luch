package test;

import static org.assertj.core.api.Assertions.*;

import model.Product;
import org.testng.annotations.Test;
import page.CatalogPage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortTests extends CommonConditions{
    @Test
    public void CheckSortLowestFirst() throws Throwable {
        CatalogPage catalogPage = new CatalogPage()
                .openPage()
                .clickSortButton()
                .clickSortByName("Price: lowest first");

        List<Product> productsList = catalogPage.getListProducts();
        List<Product> sortedList = new ArrayList<>(productsList);
        sortedList.sort(Comparator.comparing(Product::getPrice).reversed());

        assertThat(productsList).isEqualTo(sortedList);
    }

    @Test
    public void CheckSortHighestFirst() throws Throwable {
        CatalogPage catalogPage = new CatalogPage()
                .openHighestFirstPage();

        List<Product> productsList = catalogPage.getListProducts();
        List<Product> sortedList = new ArrayList<>(productsList);
        sortedList.sort(Comparator.comparing(Product::getPrice));

        assertThat(productsList).isEqualTo(sortedList);
    }

    @Test
    public void CheckSortFirstPopular() throws Throwable{
        CatalogPage catalogPage = new CatalogPage()
                .openPage()
                .clickSortButton()
                .clickSortByName("First popular");

        List<Product> productsList = catalogPage.getListProducts();
    }
}
