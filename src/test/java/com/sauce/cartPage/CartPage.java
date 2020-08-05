package com.sauce.cartPage;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import resources.Elements;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.ArrayList;
import java.util.List;

public class CartPage {
    private RemoteWebDriver driver;
    private WebDriverWait wait;

    public CartPage(RemoteWebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }

    @Step("Check for correct page")
    public boolean checkPage(){
        if(driver.getCurrentUrl().equals("https://www.saucedemo.com/cart.html")){
            takeScreenshot(driver);
            return true;
        }else{
            takeScreenshot(driver);
            return false;
        }
    }

    @Step("Check continue shopping button")
    public boolean checkContinueShopping(){
        driver.findElement(Elements.continueShopping).click();
        if(driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html")){
            takeScreenshot(driver);
            return true;
        }else{
            takeScreenshot(driver);
            return false;
        }
    }

    @Step("Check checkout")
    public boolean checkCheckout(){
        driver.findElement(Elements.checkCheckout).click();
        if(driver.getCurrentUrl().equals("https://www.saucedemo.com/checkout-step-one.html")){
            takeScreenshot(driver);
            return true;
        }else{
            takeScreenshot(driver);
            return false;
        }
    }

    @Step("Get items list")
    public List<String> getItemsList(){
        List<WebElement> listOfElements = driver.findElements(Elements.cartItemName);
        List<String> listOfNames = new ArrayList<>();
        for(int i = 0; i < listOfElements.size(); i++){
            listOfNames.add(listOfElements.get(i).getText());
        }

        return listOfNames;

    }


    @Attachment(value = "Screenshot", type = "image/png")
    private static byte[] takeScreenshot(RemoteWebDriver driver){
        return driver.getScreenshotAs(OutputType.BYTES);
    }
}
