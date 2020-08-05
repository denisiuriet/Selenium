package com.sauce.checkout;

import bsh.DelayedEvalBshMethod;
import com.sauce.cartPage.CartPage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import resources.Elements;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;
import sun.misc.REException;

public class CheckoutPage {
    private RemoteWebDriver driver;
    private WebDriverWait wait;
    private String error;

    public CheckoutPage(RemoteWebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }

    @Step("Check Page")
    public boolean checkPage(){
        if(driver.getCurrentUrl().equals("https://www.saucedemo.com/checkout-step-one.html")){
            takeScreenshot(driver);
            return true;
        }else{
            takeScreenshot(driver);
            return false;
        }
    }

    @Step("Set First Name")
    public void setFirstName(){
        driver.findElement(Elements.firstName).sendKeys("Denis");
        takeScreenshot(driver);
    }

    @Step("Set Last Name")
    public void setLastName(){
        driver.findElement(Elements.lastName).sendKeys("Denis");
        takeScreenshot(driver);
    }

    @Step("Set Postal Code")
    public void setPostalCode(){
        driver.findElement(Elements.postalCode).sendKeys("12345");
        takeScreenshot(driver);
    }

    @Step("Check Cancel Button")
    public CartPage checkCancelButton(){
        driver.findElement(Elements.cancelButton).click();
        takeScreenshot(driver);

        return new CartPage(driver, wait);
    }

    @Step("Check Success Continue Button")
    public boolean checkContinueButton(){
        driver.findElement(Elements.continueButton).click();
        if(driver.getCurrentUrl().equals("https://www.saucedemo.com/checkout-step-two.html")){
            takeScreenshot(driver);
            return true;
        }else{
            takeScreenshot(driver);
            return false;
        }
    }

    @Step("Check Fail Continue Button")
    public CheckoutPage checkFailContinueButton(){
        driver.findElement(Elements.continueButton).click();
        takeScreenshot(driver);

        return this;
    }

    @Step("Error Message")
    public String getErrorMessage(){
        error = driver.findElement(Elements.errorMessageCheckout).getText();
        takeScreenshot(driver);

        return error;
    }
    @Attachment
    public byte[] takeScreenshot(RemoteWebDriver driver){
        return driver.getScreenshotAs(OutputType.BYTES);
    }
}
