package test;

import model.Product;
import org.junit.Assert;
import org.testng.annotations.Test;
import page.CartPage;
import page.Header;
import page.ProductPage;
import service.ProductCreator;

public class ProductTests extends CommonConditions{
    @Test(groups = "ProductTests.checkCorrectAddToCart")
    public void checkCorrectAddToCart() throws Throwable {
        Product product = ProductCreator.product();

        ProductPage productPage = new ProductPage(driver)
                .openPage(product.getProductURL());
        productPage.isProductHeaderDisplyed();
        productPage.addToCart(product);

        new Header(driver).isCartNotEmpty();

        CartPage cartPage = productPage.goToCartPage();

        Assert.assertTrue(cartPage.isProductContainInCart(product));
    }
}
