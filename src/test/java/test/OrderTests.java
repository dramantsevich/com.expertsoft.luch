package test;

import model.Order;
import model.Product;
import model.User;
import org.junit.Assert;
import org.testng.annotations.Test;
import page.*;
import service.ProductCreator;
import service.UserCreator;

public class OrderTests extends CommonConditions{
    @Test(dependsOnGroups = {"ProductTests.checkCorrectAddToCart"})
    public void checkCorrectlyOrderedProduct() throws Throwable {
        User testUser = UserCreator.userForOrderPage();

        OrderPage orderPage = preConditionProductInCart()
                .goToOrderPage()
                .inputAllFieldsInOrder(testUser)
                .chooseDeliveryService(Order.DeliveryService.PICKUP)
                .choosePaymentSystem(Order.PaymentSystem.CREDIT_CART_AND_APPLE_PAY);

        PaymentPage paymentPage = orderPage.goToPaymentPage();

        Assert.assertTrue(paymentPage.isOrderCompleteMessageContain());
    }

    @Test(dependsOnGroups = {"ProductTests.checkCorrectAddToCart"})
    public void checkNotEnteredAllFieldsOnOrderedProduct() throws Throwable {
        OrderPage orderPage = preConditionProductInCart()
                .goToOrderPage();

        PaymentPage paymentPage = orderPage.goToPaymentPage();

        Assert.assertEquals(5, orderPage.getErrorMessagesCount());
    }

    @Test(dependsOnGroups = {"ProductTests.checkCorrectAddToCart"})
    public void checkNotEnteredNameAndSurnameFieldOnOrderedProduct() throws Throwable {
        User testUser = UserCreator.userForOrderPage();

        OrderPage orderPage = preConditionProductInCart()
                .goToOrderPage()
                .inputFieldsWithoutNameAndSurnameInOrder(testUser);

        PaymentPage paymentPage = orderPage.goToPaymentPage();

        Assert.assertEquals(1, orderPage.getErrorMessagesCount());
        Assert.assertTrue(orderPage.isErrorMessageEqualString("Контактное лицо this field is required"));
    }

    @Test(dependsOnGroups = {"ProductTests.checkCorrectAddToCart"})
    public void checkNotEnteredTelephoneFieldOnOrderedProduct() throws Throwable {
        User testUser = UserCreator.userForOrderPage();

        OrderPage orderPage = preConditionProductInCart()
                .goToOrderPage()
                .inputFieldsWithoutTelephoneInOrder(testUser);

        PaymentPage paymentPage = orderPage.goToPaymentPage();

        Assert.assertEquals(1, orderPage.getErrorMessagesCount());
        Assert.assertTrue(orderPage.isErrorMessageEqualString("Телефон this field is required"));
    }

    @Test(dependsOnGroups = {"ProductTests.checkCorrectAddToCart"})
    public void checkNotEnteredCityOrCountryFieldOnOrderedProduct() throws Throwable {
        User testUser = UserCreator.userForOrderPage();

        OrderPage orderPage = preConditionProductInCart()
                .goToOrderPage()
                .inputFieldsWithoutCityOrCountryInOrder(testUser);

        PaymentPage paymentPage = orderPage.goToPaymentPage();

        Assert.assertEquals(1, orderPage.getErrorMessagesCount());
        Assert.assertTrue(orderPage.isErrorMessageEqualString("Местоположение this field is required"));
    }

    @Test(dependsOnGroups = {"ProductTests.checkCorrectAddToCart"})
    public void checkNotEnteredEmailFieldOnOrderedProduct() throws Throwable {
        User testUser = UserCreator.userForOrderPage();

        OrderPage orderPage = preConditionProductInCart()
                .goToOrderPage()
                .inputFieldsWithoutEmailInOrder(testUser);

        PaymentPage paymentPage = orderPage.goToPaymentPage();

        Assert.assertEquals(2, orderPage.getErrorMessagesCount());
        Assert.assertTrue(orderPage.isErrorMessagesEqualStrings("E-Mail this field is required", "Email for registration is not specified"));
    }

    private CartPage preConditionProductInCart() throws Throwable {
        Product product = ProductCreator.product();

        ProductPage productPage = new ProductPage(driver)
                .openPage(product.getProductURL());
        productPage.isProductHeaderDisplyed();
        productPage.addToCart(product);

        new Header(driver).isCartNotEmpty();

        return productPage.goToCartPage();
    }
}
