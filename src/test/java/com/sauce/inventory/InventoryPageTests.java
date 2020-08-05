package com.sauce.inventory;

import com.sauce.SingletonDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

public class InventoryPageTests {
    private RemoteWebDriver driver;
    private WebDriverWait wait;

    private InventoryPage inventoryPage;

    public InventoryPageTests(){

    }

    @BeforeClass(groups = {"order"})
    public void driverSetup() throws MalformedURLException {
        driver = SingletonDriver.getInstance();
        wait = new WebDriverWait(driver,30);
        inventoryPage = new InventoryPage(driver, wait);
    }

    @BeforeMethod
    public void setupDriver(){
       driver.navigate().to("https://www.saucedemo.com/" + "/inventory.html");
       driver.manage().deleteAllCookies();
       driver.navigate().to("https://www.saucedemo.com/" + "/inventory.html");
    }

    @Test(groups = {"order"})
    public void checkCartButton(){
        inventoryPage.checkPage();
        inventoryPage.checkCartButton();
    }

    @Test(priority = 1)
    public void checkMenuButton(){
        inventoryPage.checkPage();
        inventoryPage.checkMenuButton();
    }

    @Test
    public void checkSortContainerOptions(){
        inventoryPage.checkPage();
        List<String> listOfElements = inventoryPage.getListOfOptions();

        List<String> sortList = listOfElements;
        //Sort the names of the options
        Collections.sort(sortList);

        Assert.assertEquals(listOfElements, sortList);
    }

    @Test(groups = {"order"}, priority = 1)
    public void checkAddToCartButton() throws InterruptedException {
        inventoryPage.checkPage();
        inventoryPage.checkNoOfItems(InventoryPage.listOfItemsNames.size());
    }

    @Test
    public void checkSortAZ(){
        inventoryPage.checkPage();
        List<String> listOfItems = inventoryPage.getItemsNames();
        Collections.sort(listOfItems);
        inventoryPage.selectOption(0);
        List<String> listOfItems2 = inventoryPage.getItemsNames();

        assertThat(listOfItems2).isEqualTo(listOfItems);
    }

    @Test
    public void checkSortZA(){
        inventoryPage.checkPage();
        List<String> listOfItems = inventoryPage.getItemsNames();
        Collections.sort(listOfItems);
        inventoryPage.selectOption(1);
        List<String> listOfItems2 = inventoryPage.getItemsNames();

        Collections.reverse(listOfItems);

        assertThat(listOfItems2).isEqualTo(listOfItems);

    }

    @Test
    public void checkSortLoHi(){
        inventoryPage.checkPage();
        List<Float> prices = inventoryPage.getPrices();
        Collections.sort(prices);

        inventoryPage.selectOption(2);
        List<Float> prices2 = inventoryPage.getPrices();

        assertThat(prices2).isEqualTo(prices);
    }

    @Test
    public void checkSortHiLo(){
        inventoryPage.checkPage();
        List<Float> prices = inventoryPage.getPrices();
        Collections.sort(prices);
        Collections.reverse(prices);

        inventoryPage.selectOption(3);
        List<Float> prices2 = inventoryPage.getPrices();

        assertThat(prices2).isEqualTo(prices);
    }

    @Test(groups = {"order"})
    public void addToCart() throws InterruptedException {
        inventoryPage.checkPage();
        inventoryPage.getLabels();
        inventoryPage.getButtons();
    }

    @AfterSuite
    public void closeDriver(){
        driver.quit();
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] takeScreenshot(RemoteWebDriver driver){
        return driver.getScreenshotAs(OutputType.BYTES);
    }
}
