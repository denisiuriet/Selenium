package com.sauce.cartPage;

import com.sauce.SingletonDriver;
import com.sauce.inventory.InventoryPage;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import resources.Elements;

import java.net.MalformedURLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CartPageTests {
    private RemoteWebDriver driver;
    private WebDriverWait wait;

    private CartPage cartPage;

    @BeforeClass(groups = {"order"})
    public void driverSetup() throws MalformedURLException {
        driver = SingletonDriver.getInstance();
        wait = new WebDriverWait(driver, 30);
        cartPage = new CartPage(driver, wait);
    }

    @BeforeMethod
    public void setupDriver() {
       driver.navigate().to("https://www.saucedemo.com/cart.html");
       driver.manage().deleteAllCookies();
       driver.navigate().to("https://www.saucedemo.com/cart.html");
    }

    @Test
    public void checkContinueShopping(){
        cartPage.checkPage();
        cartPage.checkContinueShopping();
    }

    @Test(groups = {"order"}, priority = 1)
    public void checkCheckout(){
        cartPage.checkPage();
        cartPage.checkCheckout();
    }

    @Test(groups = {"order"})
    public void checkItems(){
        cartPage.checkPage();
        List<String> listOfNames = cartPage.getItemsList();
        assertThat(listOfNames).isEqualTo(InventoryPage.listOfItemsNames);
    }

    @AfterSuite
    public void closeDriver(){
        driver.quit();
    }
}
