package test;

import model.Order;
import model.User;
import org.testng.annotations.Test;
import page.OrderPage;
import page.PaymentPage;
import service.UserCreator;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderPositiveTests extends PreConditionsForOrderTests{

    @Test(dependsOnGroups = {"ProductTests.checkCorrectAddToCart"})
    public void checkCorrectlyOrderedProduct() throws Throwable {
        User testUser = UserCreator.userForOrderPage();

        OrderPage orderPage = preConditionProductInCart()
                .inputAllFieldsInOrder(testUser)
                .chooseDeliveryService(Order.DeliveryService.PICKUP)
                .choosePaymentSystem(Order.PaymentSystem.CREDIT_CART_AND_APPLE_PAY);

        PaymentPage paymentPage = orderPage.goToPaymentPage();

        String currentUrl = paymentPage.getCurrentUrl();

        assertThat(paymentPage.getOrderCompeleteMessage().getText()).isEqualTo("Order complete");
        assertThat(currentUrl).isEqualTo(paymentPage.getCurrentUrl());
    }
}
