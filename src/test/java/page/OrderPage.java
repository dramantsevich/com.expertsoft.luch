package page;

import model.Order;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends AbstractPageWithStaticURL{
    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//label[contains(text(),'Name and Surname')]/following-sibling::input")
    private WebElement clientNameInput;

    @FindBy(xpath = "//label[contains(text(),'Telephone')]/following-sibling::input")
    private WebElement clientPhoneInput;

    @FindBy(xpath = "//label[contains(text(),'E-mail')]/following-sibling::input")
    private WebElement clientEmailInput;

    @FindBy(xpath = "//label[contains(text(),'City or Country')]//following-sibling::div//input[@class='bx-ui-sls-fake']")
    private WebElement clientCityInput;

    @FindBy(xpath = "//div[@class='bx-ui-sls-variants']")
    private WebElement clientCityInputDropDown;

    @FindBy(xpath = "//p/font[@class='errortext']")
    private List<WebElement> errorMessagesList;

    @FindBy(xpath = "//p/font[@class='errortext']")
    private WebElement errorMessage;

    By completeOrderButton = By.xpath("//a[contains(text(),'Complete Order')]");
    By loadingAPI = By.xpath("//div[contains(@id,'wait_') and contains(text(),'Loading')]");

    public OrderPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    protected OrderPage openPage() {
        driver.get("https://luch.by/en/cart/make/");
        logger.info("Order page opened");
        return null;
    }

    public OrderPage inputAllFieldsInOrder(User user) throws Throwable {
        clientNameInput.sendKeys(user.getUsername());
        clientPhoneInput.sendKeys(user.getPhone());
        clientEmailInput.sendKeys(user.getEmail());
        clientCityInput.sendKeys(user.getCity());
        clickCityFromDropDownList(user.getCity());
        logger.info("Filled fields: " +
                "userName:[" + user.getUsername() +
                "], userPhone:[" + user.getPhone() +
                "], userEmail:[" + user.getEmail() +
                "], userCity:[" + user.getCity() +"]");
        return this;
    }

    public OrderPage inputFieldsWithoutNameAndSurnameInOrder(User user) throws Throwable {
        clientPhoneInput.sendKeys(user.getPhone());
        clientEmailInput.sendKeys(user.getEmail());
        clientCityInput.sendKeys(user.getCity());
        clickCityFromDropDownList(user.getCity());
        logger.info("Filled fields: userPhone:[" + user.getPhone() +
                "], userEmail:[" + user.getEmail() +
                "], userCity:[" + user.getCity() +"]");
        return this;
    }

    public OrderPage inputFieldsWithoutTelephoneInOrder(User user) throws Throwable{
        clientNameInput.sendKeys(user.getUsername());
        clientEmailInput.sendKeys(user.getEmail());
        clientCityInput.sendKeys(user.getCity());
        clickCityFromDropDownList(user.getCity());
        logger.info("Filled fields: " +
                "userName:[" + user.getUsername() +
                "], userEmail:[" + user.getEmail() +
                "], userCity:[" + user.getCity() +"]");
        return this;
    }

    public OrderPage inputFieldsWithoutCityOrCountryInOrder(User user){
        clientNameInput.sendKeys(user.getUsername());
        clientPhoneInput.sendKeys(user.getPhone());
        clientEmailInput.sendKeys(user.getEmail());
        logger.info("Filled fields: " +
                "userName:[" + user.getUsername() +
                "], userPhone:[" + user.getPhone() +
                "], userEmail:[" + user.getEmail() +"]");
        return this;
    }

    public OrderPage inputFieldsWithoutEmailInOrder(User user) throws Throwable {
        clientNameInput.sendKeys(user.getUsername());
        clientPhoneInput.sendKeys(user.getPhone());
        clientCityInput.sendKeys(user.getCity());
        clickCityFromDropDownList(user.getCity());
        logger.info("Filled fields: " +
                "userName:[" + user.getUsername() +
                "], userPhone:[" + user.getPhone() +
                "], userCity:[" + user.getCity() +"]");
        return this;
    }

    private OrderPage clickCityFromDropDownList(String city) throws Throwable {
        waitforVisibility(clientCityInputDropDown);
        driver.findElement(By.xpath("//div[contains(@class,'dropdown-item bx-ui-sls-variant')]/span[contains(text(),'"+ city +"')]")).click();
        waitWebElementVisibilityInDOM(loadingAPI);
        logger.info("City: "+ city +" in dropdown list clicked");
        return this;
    }

    public OrderPage chooseDeliveryService(Order.DeliveryService deliveryService) throws Throwable {
        waitWebElementLocatedBy(By.xpath("//div[contains(text(),'"+ deliveryService.getValue() +"')]//ancestor::div[@class='input radio']/input"));
        driver.findElement(By.xpath("//div[contains(text(),'"+ deliveryService.getValue() +"')]//ancestor::div[@class='input radio']/input")).click();
        waitWebElementVisibilityInDOM(loadingAPI);
        logger.info("Delivery service: " + deliveryService.getValue() +" clicked");
        return this;
    }

    public OrderPage choosePaymentSystem(Order.PaymentSystem paymentSystem) throws Throwable {
        waitWebElementLocatedBy(By.xpath("//div[contains(text(),'"+ paymentSystem.getValue() +"')]//ancestor::div[@class='input radio']/input"));
        driver.findElement(By.xpath("//div[contains(text(),'"+ paymentSystem.getValue() +"')]//ancestor::div[@class='input radio']/input")).click();
        waitWebElementVisibilityInDOM(loadingAPI);
        logger.info("Delivery service: " + paymentSystem.getValue() +" clicked");
        return this;
    }

    public PaymentPage goToPaymentPage() throws Throwable {
        waitWebElementLocatedBy(completeOrderButton);
        driver.findElement(completeOrderButton).click();
        waitWebElementVisibilityInDOM(loadingAPI);
        logger.info("Clicked to 'Complete order' on cart page");
        return new PaymentPage(driver);
    }

    public int getErrorMessagesCount () {
        logger.info("Size of error messages: ["+ errorMessagesList.size() +"]");
        return errorMessagesList.size();
    }

    public boolean isErrorMessageEqualString(String error){
        boolean isMessageEqual = false;

        if(errorMessage.getText().equals(error)){
            isMessageEqual = true;
        }

        logger.info("Expected error message: ["+ error +"]" +
                " Actual error message : ["+ errorMessage.getText() +"]");

        return isMessageEqual;
    }

    public boolean isErrorMessagesEqualStrings(String error1, String error2){
        boolean isMessageEqual = false;
        int equalsMessagesCount = 0;

        for(WebElement el : errorMessagesList){
            if(el.getText().equals(error1) || el.getText().equals(error2)){
                equalsMessagesCount++;
                logger.info("Expected error message: ["+ error1 +", "+ error2 +"]" +
                        " Actual error message : ["+ errorMessage.getText() +"]");
            }
        }

        if(getErrorMessagesCount() == equalsMessagesCount){
            isMessageEqual = true;
        }

        return isMessageEqual;
    }
}
