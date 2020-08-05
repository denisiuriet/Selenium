package com.sauce.logIn;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import resources.Elements;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

public class LogInPage {
    RemoteWebDriver driver;
    String error;
    WebDriverWait wait;

    public LogInPage(RemoteWebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }

    @Step("Checking for correct page")
    public boolean checkPage(){
        if(driver.getCurrentUrl().toString().equals("https://www.saucedemo.com/")){
            takeScreenshot(driver);
            return true;
        }else{
            takeScreenshot(driver);
            return false;
        }
    }

    @Step("Set username")
    public void setUsername(String setUsername){
        driver.findElement(Elements.username).sendKeys(setUsername);
        takeScreenshot(driver);
    }

    @Step("Set password")
    public void setPassword(String setPassword){
        driver.findElement(Elements.password).sendKeys(setPassword);
        takeScreenshot(driver);
    }

   @Step("Click login Success")
    public boolean checkLoginSuccess(){
        driver.findElement(Elements.loginButton).click();
        if(driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html")){
            takeScreenshot(driver);
            return true;
        }else{
            takeScreenshot(driver);
            return false;
        }
    }

    @Step("Click login Fail")
    public LogInPage checkLoginFail(){
        driver.findElement(Elements.loginButton).click();
        takeScreenshot(driver);
        return this;
    }

    @Step("Error message")
    public String getError(){
        error = driver.findElement(Elements.errorMessage).getText();
        takeScreenshot(driver);

        return error;
    }

    @Attachment(value = "Screenshot", type = "image/png")
    private static byte[] takeScreenshot(RemoteWebDriver driver){
        return driver.getScreenshotAs(OutputType.BYTES);
    }
}
