package test;

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

        Assert.assertTrue(mainPage.isFormOneClickOrderSuccessfullMessageVisible(), "form of one click order is successfully work");
    }

    @Test
    public void checkValidationErrorMessageIfNameIsEmpty() throws Throwable {
        User testUserWithoutName = UserCreator.userForOneClickOrderWithoutName();
        MainPage mainPage = openPageAndClickToWatchesOneClickOrder(driver)
                .inputClientPhoneField(testUserWithoutName)
                .submitFormOneClickOrder();

        Assert.assertTrue(mainPage.isRequiredClientNameInputWillPopupError(), "element is required and validation error will popup if the field is empty");
    }

    @Test
    public void checkValidationErrorMessageIfPhoneIsEmpty() throws Throwable {
        User testUserWithoutName = UserCreator.userForOneClickOrderWithoutPhone();
        MainPage mainPage = openPageAndClickToWatchesOneClickOrder(driver)
                .inputClientNameField(testUserWithoutName)
                .submitFormOneClickOrder();

        Assert.assertTrue(mainPage.isRequiredClientPhoneInputWillPopupError(), "element is required and validation error will popup if the field is empty");
    }

    private static MainPage openPageAndClickToWatchesOneClickOrder(WebDriver driver) throws Throwable {
        return new MainPage(driver)
                .openPage()
                .clickWatchesToOneClickOrder(1);
    }
}
