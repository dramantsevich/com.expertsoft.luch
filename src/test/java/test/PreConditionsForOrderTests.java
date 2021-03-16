package test;

import static org.assertj.core.api.Assertions.*;

import model.Product;
import page.Header;
import page.OrderPage;
import page.ProductPage;
import service.ProductCreator;

public abstract class PreConditionsForOrderTests extends CommonConditions{
    protected OrderPage preConditionProductInCart() throws Throwable {
        Product product = ProductCreator.product();

        ProductPage productPage = new ProductPage()
                .openPage(product.getProductURL())
                .addToCart(product);

        assertThat(Integer.parseInt(new Header().getIconProductsInCartCount().getText())).isGreaterThan(0);

        return productPage.goToCartPage().goToOrderPage();
    }
}
