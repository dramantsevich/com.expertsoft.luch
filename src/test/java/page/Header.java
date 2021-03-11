package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Header extends AbstarctPage{
    @FindBy(xpath = "//div[@class='shopping-cart !']/div[@class='count']")
    private WebElement iconProductsInCartCount;

    public Header(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public boolean isCartNotEmpty() throws Throwable{
        waitforVisibility(iconProductsInCartCount);
        return Integer.parseInt(iconProductsInCartCount.getText()) > 0;
    }
}
