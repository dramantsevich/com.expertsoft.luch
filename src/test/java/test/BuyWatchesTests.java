package test;

import model.User;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.MainPage;
import service.UserCreator;

public class BuyWatchesTests extends CommonConditions{

    @Test(description = "User select wathces and click \"One click order\"")
    public void oneClickOrderTest() throws Throwable {
        User testUser = UserCreator.userForOnceClickOrder();
        MainPage mainPage = new MainPage(driver)
                .openPage()
                .clickWatchesToOneClickOrder(1)
                .inputFieldsInOneClickOrderPopup(testUser)
                .submitFormOneClickOrder();

        Assert.assertTrue(mainPage.isFormOneClickOrderSuccessfullMessageVisible(), "form of one click order is successfully work");
    }
}
