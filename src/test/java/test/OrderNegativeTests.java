package test;

import static org.assertj.core.api.Assertions.*;

import model.User;
import org.testng.annotations.Test;
import page.*;
import service.UserCreator;

public class OrderNegativeTests extends PreConditionsForOrderTests{
    @Test(dependsOnGroups = {"ProductTests.checkCorrectAddToCart"})
    public void checkNotEnteredAllFieldsOnOrderedProduct() throws Throwable {
        int expectedCount = 5;
        OrderPage orderPage = preConditionProductInCart();

        PaymentPage paymentPage = orderPage.goToPaymentPage();

        assertThat(orderPage.getErrorMessageList().size()).isEqualTo(expectedCount);
    }

    @Test(dependsOnGroups = {"ProductTests.checkCorrectAddToCart"})
    public void checkNotEnteredNameAndSurnameFieldOnOrderedProduct() throws Throwable {
        User testUser = UserCreator.userForOrderPage();
        int expectedCount = 1;
        String expectedErrorMessage = "Контактное лицо this field is required";

        OrderPage orderPage = preConditionProductInCart()
                .inputFieldsWithoutNameAndSurnameInOrder(testUser);

        PaymentPage paymentPage = orderPage.goToPaymentPage();

        assertThat(orderPage.getErrorMessageList().size()).isEqualTo(expectedCount);
        assertThat(orderPage.getErrorMessage().getText()).isEqualTo(expectedErrorMessage);
    }

    @Test(dependsOnGroups = {"ProductTests.checkCorrectAddToCart"})
    public void checkNotEnteredTelephoneFieldOnOrderedProduct() throws Throwable {
        User testUser = UserCreator.userForOrderPage();
        int expectedCount = 1;
        String expectedErrorMessage = "Телефон this field is required";

        OrderPage orderPage = preConditionProductInCart()
                .inputFieldsWithoutTelephoneInOrder(testUser);

        PaymentPage paymentPage = orderPage.goToPaymentPage();

        assertThat(orderPage.getErrorMessageList().size()).isEqualTo(expectedCount);
        assertThat(orderPage.getErrorMessage().getText()).isEqualTo(expectedErrorMessage);
    }

    @Test(dependsOnGroups = {"ProductTests.checkCorrectAddToCart"})
    public void checkNotEnteredCityOrCountryFieldOnOrderedProduct() throws Throwable {
        User testUser = UserCreator.userForOrderPage();
        int expectedCount = 1;
        String expectedErrorMessage = "Местоположение this field is required";

        OrderPage orderPage = preConditionProductInCart()
                .inputFieldsWithoutCityOrCountryInOrder(testUser);

        PaymentPage paymentPage = orderPage.goToPaymentPage();

        assertThat(orderPage.getErrorMessageList().size()).isEqualTo(expectedCount);
        assertThat(orderPage.getErrorMessage().getText()).isEqualTo(expectedErrorMessage);
    }

    @Test(dependsOnGroups = {"ProductTests.checkCorrectAddToCart"})
    public void checkNotEnteredEmailFieldOnOrderedProduct() throws Throwable {
        User testUser = UserCreator.userForOrderPage();
        int expectedCount = 2;
        String expectedFirstErrorMessage = "Email for registration is not specified";
        String expectedSecondErrorMessage = "E-Mail this field is required";

        OrderPage orderPage = preConditionProductInCart()
                .inputFieldsWithoutEmailInOrder(testUser);

        PaymentPage paymentPage = orderPage.goToPaymentPage();

        assertThat(orderPage.getErrorMessageList().size()).isEqualTo(expectedCount);
        assertThat(orderPage.getErrorMessageList().get(0).getText()).isEqualTo(expectedFirstErrorMessage);
        assertThat(orderPage.getErrorMessageList().get(1).getText()).isEqualTo(expectedSecondErrorMessage);
    }
}
