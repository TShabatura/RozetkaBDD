package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPopup extends BasePage{
    public CartPopup(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='cart-receipt__sum-price']/span")
    private WebElement totalAmountOfCart;

    public int getTotalAmountOfCart(){
        return Integer.valueOf(totalAmountOfCart.getText());
    }
}
