package com.sauce.logIn;

import com.sauce.SingletonDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import resources.Elements;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.net.MalformedURLException;

import static org.assertj.core.api.Assertions.assertThat;

public class LogInTests {
    private RemoteWebDriver driver;
    private WebDriverWait wait;

    private LogInPage logInPage;

    public LogInTests(){}

    @BeforeClass(groups = {"order"})
    public void driverSetup() throws MalformedURLException{
        driver = SingletonDriver.getInstance();
        wait = new WebDriverWait(driver, 30);
        logInPage = new LogInPage(driver, wait);
    }

    @BeforeMethod(groups = {"order"})
    public void setupDriver() {
        driver.navigate().to("https://www.saucedemo.com/");
        driver.manage().deleteAllCookies();
        driver.navigate().to("https://www.saucedemo.com/");
    }

    @Test(groups = {"order"})
     public void validCredentials() throws InterruptedException{
         logInPage.checkPage();
         logInPage.setUsername("standard_user");
         logInPage.setPassword("secret_sauce");
         logInPage.checkLoginSuccess();
     }

    @Test
    public void invalidCredentials() throws InterruptedException{
        logInPage.checkPage();
        logInPage.setUsername("invalid");
        logInPage.setPassword("invalid");
        logInPage.checkLoginFail();

        wait.until(ExpectedConditions.visibilityOfElementLocated(Elements.errorMessage));
        takeScreenshot(driver);
        if(logInPage.getError().contains("Epic sadface")){
            assertThat("Epic sadface: Username and password do not match any user in this service").isEqualTo(logInPage.getError());
        }else{
            assertThat(Elements.invalidLoginError).isEqualTo(logInPage.getError());
        }
    }

    @Test
    public void checkUsername() throws InterruptedException{
        logInPage.checkPage();
        logInPage.setPassword("invalid");
        logInPage.checkLoginFail();
        wait.until(ExpectedConditions.visibilityOfElementLocated(Elements.errorMessage));
        takeScreenshot(driver);

        assertThat(Elements.userError).isEqualTo(logInPage.getError());
    }

    @Test
    public void checkPassword() throws InterruptedException{
        logInPage.checkPage();
        logInPage.setUsername("invalid");
        logInPage.checkLoginFail();
        wait.until(ExpectedConditions.visibilityOfElementLocated(Elements.errorMessage));
        takeScreenshot(driver);

        assertThat(Elements.passError).isEqualTo(logInPage.getError());
    }

    @Test
    public void missingCredentials() {
        logInPage.checkPage();
        logInPage.checkLoginFail();
        wait.until(ExpectedConditions.visibilityOfElementLocated(Elements.errorMessage));
        takeScreenshot(driver);

        assertThat(Elements.userError).isEqualTo(logInPage.getError());
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
