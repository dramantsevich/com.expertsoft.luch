package test;

import static org.assertj.core.api.Assertions.*;

import model.User;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.MainPage;
import service.UserCreator;

public class OneClickOrderTests extends CommonConditions{

    @Test
    public void checkCorrectOneClickOrder() throws Throwable {
        User testUser = UserCreator.userForOnceClickOrder();
        MainPage mainPage = openPageAndClickToWatchesOneClickOrder(driver)
                .inputFieldsInOneClickOrderPopup(testUser)
                .submitFormOneClickOrder();

        assertThat(mainPage.isFormOneClickOrderSuccessfullMessageVisible()).isTrue();
    }

    @Test
    public void checkValidationErrorMessageIfNameIsEmpty() throws Throwable {
        User testUserWithoutName = UserCreator.userForOneClickOrderWithoutName();
        MainPage mainPage = openPageAndClickToWatchesOneClickOrder(driver)
                .inputClientPhoneField(testUserWithoutName)
                .submitFormOneClickOrder();

        assertThat(mainPage.isRequiredClientNameInputWillPopupError()).isTrue();
    }

    @Test
    public void checkValidationErrorMessageIfPhoneIsEmpty() throws Throwable {
        User testUserWithoutName = UserCreator.userForOneClickOrderWithoutPhone();
        MainPage mainPage = openPageAndClickToWatchesOneClickOrder(driver)
                .inputClientNameField(testUserWithoutName)
                .submitFormOneClickOrder();

        assertThat(mainPage.isRequiredClientPhoneInputWillPopupError()).isTrue();
    }

    private static MainPage openPageAndClickToWatchesOneClickOrder(WebDriver driver) throws Throwable {
        return new MainPage(driver)
                .openPage()
                .clickWatchesToOneClickOrder(1);
    }
}
