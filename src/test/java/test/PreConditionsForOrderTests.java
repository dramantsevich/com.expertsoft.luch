package test;

import static org.assertj.core.api.Assertions.*;

import model.Product;
import page.Header;
import page.OrderPage;
import page.ProductPage;
import service.ProductCreator;

public abstract class PreConditionsForOrderTests extends CommonConditions{
    public static final String PRODUCT_FIRST_URL = "test.data.product.first.url";

    protected OrderPage preConditionProductInCart() throws Throwable {

        Product product = ProductCreator.product(PRODUCT_FIRST_URL);

        ProductPage productPage = new ProductPage()
                .openPage(product.getProductURL())
                .addToCart(product);

        assertThat(Integer.parseInt(new Header().getIconProductsInCartCount().getText())).isGreaterThan(0);

        return productPage.goToCartPage().goToOrderPage();
    }
}
