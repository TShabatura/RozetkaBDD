package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import manager.PageFactoryManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pagefactory.CartPopup;
import pagefactory.HomePage;
import pagefactory.ProductCatalogPage;

import java.util.concurrent.TimeUnit;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;

public class StepDefinition {

    WebDriver driver;
    private CartPopup cartPopup;
    private HomePage homePage;
    private ProductCatalogPage productCatalogPage;
    private PageFactoryManager pageFactoryManager;

    @Before
    public void testsSetUp() {
        chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        pageFactoryManager = new PageFactoryManager(driver);
        cartPopup = pageFactoryManager.getCartPopup();
        homePage = pageFactoryManager.getHomePage();
        productCatalogPage = pageFactoryManager.getProductCatalogPage();
    }

    @After
    public void tearDown(){
        driver.close();
    }

    @Given("user is on Home Page")
    public void openHomePage() {
        driver.get("https://rozetka.com.ua/ua/");
    }

    @When("user search for {string}")
    public void inputSearchData(final String groupOfThings){
        homePage.inputSearchData(groupOfThings);
        productCatalogPage.waitForPageLoadingComplete(60);
    }

    @And("user filter items by {string}")
    public void filterByProducer(final String producer){
        productCatalogPage.searchByProducer(producer);
        productCatalogPage.waitForFiltering(60, By.xpath("//div[@data-filter-name='producer']//label"));
        productCatalogPage.filterByProducer();
        productCatalogPage.waitForPageLoadingComplete(60);
    }

    @And("user add item to cart")
    public void clickAddToCartButton(){
        productCatalogPage.clickAddToCartButton();
    }

    @And("user open cart")
    public void clickCartButton(){
        productCatalogPage.clickCartButton();
        productCatalogPage.waitForVisibilityOfElement(60, productCatalogPage.getPopup());
    }

    @Then("user check that total amount of cart is lower than {int}")
    public void checkThatTotalAmountIsLowerThan(int totalAmount){
        Assert.assertTrue(cartPopup.getTotalAmountOfCart() < totalAmount);
    }


}
