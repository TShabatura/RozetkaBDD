package manager;

import org.openqa.selenium.WebDriver;
import pagefactory.*;

public class PageFactoryManager {

    WebDriver driver;

    public PageFactoryManager(WebDriver driver) {
        this.driver = driver;
    }

    public CartPopup getCartPopup(){
        return new CartPopup(driver);
    }

    public HomePage getHomePage(){
        return new HomePage(driver);
    }

    public ProductCatalogPage getProductCatalogPage(){
        return new ProductCatalogPage(driver);
    }
}
