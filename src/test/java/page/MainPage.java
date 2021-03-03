package page;

import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends AbstarctPage{
    private final String BASE_URL = "https://luch.by/en/";
    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//div[@class='w-popup w-pop-oneclick _js-pop-oneclick']")
    private WebElement oneClickOrderPopup;

    @FindBy(xpath = "//label[contains(text(),'Your name')]/following-sibling::input")
    private WebElement clientNameInput;

    @FindBy(xpath = "//label[contains(text(),'Phone')]/following-sibling::input")
    private WebElement clientPhoneInput;

    @FindBy(xpath = "//label[contains(text(),'E-mail')]/following-sibling::input")
    private WebElement clientEmailInput;

    @FindBy(xpath = "//input[@type='submit' and @class='button _transparent']")
    private WebElement submitButtonOneClickOrderForm;

    @FindBy(xpath = "//div[@class='form_result pop-body success']")
    private WebElement formOneClickOrderSuccessfullMessage;

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public MainPage openPage() {
        driver.navigate().to(BASE_URL);
        logger.info("Main page opened");
        return this;
    }

    public MainPage clickWatchesToOneClickOrder(int index) throws Throwable {
        driver.findElement(By.xpath("(//div[@class='owl-item active']//span[@class='oneclick-btn _big _js-b-pop-oneclick'])["+ index +"]"))
                .click();
        waitforVisibility(oneClickOrderPopup);
        logger.info("Click to 'One click order' button");
        return new MainPage(driver);
    }

    public MainPage inputFieldsInOneClickOrderPopup(User user){
        clientNameInput.sendKeys(user.getUsername());
        clientPhoneInput.sendKeys(user.getPhone());
        clientEmailInput.sendKeys(user.getEmail());
        logger.info("Filled fields: " +
                "userName:[" + user.getUsername() +
                "], userPhone:[" + user.getPhone() +
                "], userEmail:[" + user.getEmail() + "]");
        return new MainPage(driver);
    }

    public MainPage submitFormOneClickOrder() {
        submitButtonOneClickOrderForm.click();
        logger.info("Click submit button in 'One click order' form");
        return new MainPage(driver);
    }

    public boolean isFormOneClickOrderSuccessfullMessageVisible() throws Throwable {
        waitforVisibility(formOneClickOrderSuccessfullMessage);
        return formOneClickOrderSuccessfullMessage.isDisplayed();
    }
}
