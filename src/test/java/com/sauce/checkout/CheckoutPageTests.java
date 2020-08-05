package com.sauce.checkout;

import com.sauce.SingletonDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import resources.Elements;
import ru.yandex.qatools.allure.annotations.Attachment;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckoutPageTests {
    private RemoteWebDriver driver;
    private WebDriverWait wait;

    private CheckoutPage checkoutPage;

    @BeforeClass(groups = {"order"})
    public void driverSetup(){
        driver = SingletonDriver.getInstance();
        wait = new WebDriverWait(driver, 30);
        checkoutPage = new CheckoutPage(driver, wait);
    }

    @BeforeMethod
    public void setupDriver(){
        driver.navigate().to("https://www.saucedemo.com/checkout-step-one.html");
        driver.manage().deleteAllCookies();
        driver.navigate().to("https://www.saucedemo.com/checkout-step-one.html");
    }


    @Test
    public void missingFirstName(){
        checkoutPage.checkPage();
        checkoutPage.checkFailContinueButton();

        wait.until(ExpectedConditions.visibilityOfElementLocated(Elements.errorMessageCheckout));
        takeScreenshot(driver);

        assertThat(Elements.firstNameError).isEqualTo(checkoutPage.getErrorMessage());
    }

    @Test
    public void missingLastName(){
        checkoutPage.checkPage();
        checkoutPage.setFirstName();
        checkoutPage.checkFailContinueButton();

        wait.until(ExpectedConditions.visibilityOfElementLocated(Elements.errorMessageCheckout));
        takeScreenshot(driver);

        assertThat(Elements.lastNameError).isEqualTo(checkoutPage.getErrorMessage());
    }

    @Test
    public void missingPostalCode(){
        checkoutPage.checkPage();
        checkoutPage.setFirstName();
        checkoutPage.setLastName();
        checkoutPage.checkFailContinueButton();

        wait.until(ExpectedConditions.visibilityOfElementLocated(Elements.errorMessageCheckout));
        takeScreenshot(driver);

        assertThat(Elements.postalCodeError).isEqualTo(checkoutPage.getErrorMessage());
    }

    @Test(groups = {"order"})
    public void validCheckout(){
        checkoutPage.checkPage();
        checkoutPage.setFirstName();
        checkoutPage.setLastName();
        checkoutPage.setPostalCode();

        checkoutPage.checkContinueButton();
    }

    @AfterSuite
    public void closeDriver(){
        driver.quit();
    }

    @Attachment(value = "Screenshot", type = "image/png")
    private static byte[] takeScreenshot(RemoteWebDriver driver){
        return driver.getScreenshotAs(OutputType.BYTES);
    }

}
