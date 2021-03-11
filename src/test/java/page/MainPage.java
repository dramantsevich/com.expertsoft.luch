package page;

import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends AbstractPageWithStaticURL{
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
        return this;
    }

    public MainPage inputFieldsInOneClickOrderPopup(User user){
        clientNameInput.sendKeys(user.getUsername());
        clientPhoneInput.sendKeys(user.getPhone());
        clientEmailInput.sendKeys(user.getEmail());
        logger.info("Filled fields: " +
                "userName:[" + user.getUsername() +
                "], userPhone:[" + user.getPhone() +
                "], userEmail:[" + user.getEmail() + "]");
        return this;
    }

    public MainPage inputClientPhoneField(User user){
        clientPhoneInput.sendKeys(user.getPhone());
        logger.info("Filled client phone input: [" + user.getPhone() +"]");
        return this;
    }

    public MainPage inputClientNameField(User user) throws Throwable {
        waitforVisibility(clientNameInput);
        clientNameInput.sendKeys(user.getUsername());
        logger.info("Filled client name input: [" + user.getUsername() +"]");
        return this;
    }

    public MainPage submitFormOneClickOrder() {
        submitButtonOneClickOrderForm.click();
        logger.info("Click submit button in 'One click order' form");
        return this;
    }

    public boolean isFormOneClickOrderSuccessfullMessageVisible() throws Throwable {
        waitforVisibility(formOneClickOrderSuccessfullMessage);
        logger.info("User see 'One click order' successful message");
        return formOneClickOrderSuccessfullMessage.isDisplayed();
    }

    public boolean isRequiredClientNameInputWillPopupError(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        boolean isRequired = (Boolean) js.executeScript("return arguments[0].required;", clientNameInput);
        logger.info("User see 'Please fill out this field.'");
        return isRequired;
    }

    public boolean isRequiredClientPhoneInputWillPopupError(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        boolean isRequired = (Boolean) js.executeScript("return arguments[0].required;", clientPhoneInput);
        logger.info("User see 'Please fill out this field.'");
        return isRequired;
    }
}
