package test;

import static org.assertj.core.api.Assertions.*;

import model.Product;
import org.testng.annotations.Test;
import page.CatalogPage;
import util.CustomDataProvider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortTests extends CommonConditions{
//    @Test(dataProvider = "SortProvider", dataProviderClass = CustomDataProvider.class)
//    public void checkCorrectSort(String sortName) throws Throwable {
//        CatalogPage catalogPage = new CatalogPage()
//                .openPage()
//                .clickSortButton()
//                .clickSortByName(sortName);
//
//        List<Product> productsList = catalogPage.getListProducts();
//        List<Product> sortedList = new ArrayList<Product>(productsList);
//        sortedList.sort(Comparator.comparing(Product::getPrice).reversed());
//
//        assertThat(productsList).isEqualTo(sortedList);
//    }
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
    public void CheckSortFirstPopular() throws Throwable{
        CatalogPage catalogPage = new CatalogPage()
                .openPage()
                .clickSortButton()
                .clickSortByName("First popular");

        List<Product> productsList = catalogPage.getListProducts();
    }
}
