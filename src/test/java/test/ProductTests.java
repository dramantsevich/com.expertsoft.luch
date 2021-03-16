package test;

import static org.assertj.core.api.Assertions.*;

import model.Product;
import org.testng.annotations.Test;
import page.CartPage;
import page.Header;
import page.ProductPage;
import service.ProductCreator;

public class ProductTests extends CommonConditions{
    @Test(groups = "ProductTests.checkCorrectAddToCart")
    public void checkCorrectAddToCart() throws Throwable {
        Product product = ProductCreator.product();
        int expectedCount = 1;

        ProductPage productPage = new ProductPage()
                .openPage(product.getProductURL())
                .addToCart(product);

        assertThat(Integer.parseInt(new Header().getIconProductsInCartCount().getText())).isGreaterThan(0);

        CartPage cartPage = productPage.goToCartPage();

        assertThat(product.getCount()).isEqualTo(expectedCount);
        assertThat(product.getProductURL()).contains(cartPage.getProductInCartByUrl(product).getText());
    }
}
