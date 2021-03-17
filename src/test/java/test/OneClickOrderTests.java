package test;

import static org.assertj.core.api.Assertions.*;

import model.User;
import org.testng.annotations.Test;
import page.MainPage;
import service.UserCreator;

public class OneClickOrderTests extends CommonConditions{

    @Test
    public void checkCorrectOneClickOrder() throws Throwable {
        User testUser = UserCreator.userForOnceClickOrder();
        String expectedMessage = "Your message was sent successfully";

        MainPage mainPage = openPageAndClickToWatchesOneClickOrder()
                .inputFieldsInOneClickOrderPopup(testUser)
                .submitFormOneClickOrder();

        assertThat(mainPage.getFormOneClickOrderSuccessfullMessage().getText()).isEqualTo(expectedMessage);
    }

    @Test
    public void checkValidationErrorMessageIfNameIsEmpty() throws Throwable {
        User testUserWithoutName = UserCreator.userForOneClickOrderWithoutName();
        MainPage mainPage = openPageAndClickToWatchesOneClickOrder()
                .inputClientPhoneField(testUserWithoutName)
                .submitFormOneClickOrder();

        assertThat(mainPage.isRequiredInputWillPopupError(mainPage.getClientNameInput())).isTrue();
    }

    @Test
    public void checkValidationErrorMessageIfPhoneIsEmpty() throws Throwable {
        User testUserWithoutName = UserCreator.userForOneClickOrderWithoutPhone();
        MainPage mainPage = openPageAndClickToWatchesOneClickOrder()
                .inputClientNameField(testUserWithoutName)
                .submitFormOneClickOrder();

        assertThat(mainPage.isRequiredInputWillPopupError(mainPage.getClientPhoneInput())).isTrue();
    }

    private static MainPage openPageAndClickToWatchesOneClickOrder() throws Throwable {
        return new MainPage()
                .openPage()
                .clickWatchesToOneClickOrder(1);
    }
}
