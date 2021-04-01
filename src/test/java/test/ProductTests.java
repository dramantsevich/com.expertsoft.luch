package test;

import static org.assertj.core.api.Assertions.*;

import model.Product;
import org.testng.annotations.Test;
import page.CartPage;
import page.Header;
import page.ProductPage;
import service.ProductCreator;

public class ProductTests extends CommonConditions{
    public static final String PRODUCT_FIRST_URL = "test.data.product.first.url";
    public static final String PRODUCT_SECOND_URL = "test.data.product.second.url";

    @Test(groups = "ProductTests.checkCorrectAddToCart")
    public void checkCorrectAddToCart() throws Throwable {
        Product product = ProductCreator.product(PRODUCT_FIRST_URL);

        CartPage cartPage = preConditionProductInCart(product);

        assertThat(product.getCount()).isEqualTo(cartPage.getListProducts().size());
        assertThat(product.getProductURL()).contains(cartPage.getProductInCartByUrl(product).getText());
    }

    @Test
    public void checkCorrectAddMoreProductQuantity() throws Throwable {
        Product product = ProductCreator.product(PRODUCT_FIRST_URL);

        CartPage cartPage = preConditionProductInCart(product)
                .addOneMoreProductQuantity(product);

        assertThat(product.getCount()).isEqualTo(cartPage.getListProducts().size());
        assertThat(cartPage.getTotalSum()).isEqualTo(product.getPrice() * cartPage.getQuantityCountByProduct(product));
    }

    @Test
    public void checkCorrectDeleteProduct() throws Throwable {
        Product product = ProductCreator.product(PRODUCT_FIRST_URL);
        String expectedMessage = "YOUR SHOPPING CART IS EMPTY";

        CartPage cartPage = preConditionProductInCart(product)
                .clickDeleteButtonByProduct(product);

        assertThat(cartPage.getEmptyCartMessage().getText()).isEqualTo(expectedMessage);
    }

    @Test
    public void checkCorrectTotalPriceOfTwoProducts() throws Throwable {
        Product firstProduct = ProductCreator.product(PRODUCT_FIRST_URL);
        Product secondProduct = ProductCreator.product(PRODUCT_SECOND_URL);

        CartPage firstCartPage = preConditionProductInCart(firstProduct);
        CartPage secondCartPage = preConditionProductInCart(secondProduct);

        assertThat(secondCartPage.getTotalSum()).isEqualTo(firstProduct.getPrice() + secondProduct.getPrice());
    }

    private CartPage preConditionProductInCart(Product product) throws Throwable {
        ProductPage productPage = new ProductPage()
                .openPage(product.getProductURL())
                .addToCart(product);

        assertThat(Integer.parseInt(new Header().getIconProductsInCartCount().getText())).isGreaterThan(0);

        return productPage.goToCartPage();
    }
}
