package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Header extends AbstarctPage{
    @FindBy(xpath = "//div[@class='shopping-cart !']/div[@class='count']")
    private WebElement iconProductsInCartCount;

    public Header() throws Throwable {
        super();
        PageFactory.initElements(this.driver, this);
    }

    public WebElement getIconProductsInCartCount() throws Throwable {
        waitforVisibility(iconProductsInCartCount);
        return iconProductsInCartCount;
    }
}
