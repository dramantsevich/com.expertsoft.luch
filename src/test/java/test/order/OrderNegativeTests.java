package test.order;

import static org.assertj.core.api.Assertions.*;

import model.User;
import org.testng.annotations.Test;
import page.*;
import service.UserCreator;
import test.PreConditionsForOrderTests;

import java.util.Arrays;
import java.util.List;

public class OrderNegativeTests extends PreConditionsForOrderTests {
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
        List<String> expectedErrors = Arrays.asList("Контактное лицо this field is required");

        OrderPage orderPage = preConditionProductInCart()
                .inputFieldsWithoutNameAndSurnameInOrder(testUser);

        PaymentPage paymentPage = orderPage.goToPaymentPage();

        assertThat(orderPage.getErrorMessageList().size()).isEqualTo(expectedErrors.size());
        assertThat(orderPage.getErrorMessage().getText()).isEqualTo(expectedErrors.stream().findFirst().map(Object::toString).orElse(null));
    }

    @Test(dependsOnGroups = {"ProductTests.checkCorrectAddToCart"})
    public void checkNotEnteredTelephoneFieldOnOrderedProduct() throws Throwable {
        User testUser = UserCreator.userForOrderPage();
        List<String> expectedErrors = Arrays.asList("Телефон this field is required");

        OrderPage orderPage = preConditionProductInCart()
                .inputFieldsWithoutTelephoneInOrder(testUser);

        PaymentPage paymentPage = orderPage.goToPaymentPage();

        assertThat(orderPage.getErrorMessageList().size()).isEqualTo(expectedErrors.size());
        assertThat(orderPage.getErrorMessage().getText()).isEqualTo(expectedErrors.stream().findFirst().map(Object::toString).orElse(null));
    }

    @Test(dependsOnGroups = {"ProductTests.checkCorrectAddToCart"})
    public void checkNotEnteredCityOrCountryFieldOnOrderedProduct() throws Throwable {
        User testUser = UserCreator.userForOrderPage();
        List<String> expectedErrors = Arrays.asList("Местоположение this field is required");

        OrderPage orderPage = preConditionProductInCart()
                .inputFieldsWithoutCityOrCountryInOrder(testUser);

        PaymentPage paymentPage = orderPage.goToPaymentPage();

        assertThat(orderPage.getErrorMessageList().size()).isEqualTo(expectedErrors.size());
        assertThat(orderPage.getErrorMessage().getText()).isEqualTo(expectedErrors.stream().findFirst().map(Object::toString).orElse(null));
    }

    @Test(dependsOnGroups = {"ProductTests.checkCorrectAddToCart"})
    public void checkNotEnteredEmailFieldOnOrderedProduct() throws Throwable {
        User testUser = UserCreator.userForOrderPage();
        List<String> expectedErrors = Arrays.asList("Email for registration is not specified", "E-Mail this field is required");

        OrderPage orderPage = preConditionProductInCart()
                .inputFieldsWithoutEmailInOrder(testUser);

        PaymentPage paymentPage = orderPage.goToPaymentPage();

        assertThat(orderPage.getErrorMessageList().size()).isEqualTo(expectedErrors.size());
        assertThat(orderPage.getErrorMessageList().get(0).getText()).isEqualTo(expectedErrors.stream().findFirst().map(Object::toString).orElse(null));
        assertThat(orderPage.getErrorMessageList().get(1).getText()).isEqualTo(expectedErrors.get(expectedErrors.size() - 1));
    }
}
