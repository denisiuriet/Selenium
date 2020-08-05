package com.sauce.inventory;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import resources.Elements;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.ArrayList;
import java.util.List;

public class InventoryPage {
    RemoteWebDriver driver;
    WebDriverWait wait;
    public static List<String> listOfItemsNames;

    public List<String> getListOfItemsNames() {
        return listOfItemsNames;
    }

    public InventoryPage(RemoteWebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }

    @Step("Check for correct Page")
    public boolean checkPage(){
        if(driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html")){
            takeScreenshot(driver);
            return true;
        }else{
            takeScreenshot(driver);
            return false;
        }
    }

    @Step("Check cart button")
    public boolean checkCartButton(){
        driver.findElement(Elements.cart).click();
        if(driver.getCurrentUrl().equals("https://www.saucedemo.com/cart.html")){
            takeScreenshot(driver);
            return true;
        }else{
            takeScreenshot(driver);
            return false;
        }
    }

    @Step("Check menu button")
    public boolean checkMenuButton(){
        driver.findElement(Elements.menuButton).click();
        if(driver.findElement(Elements.menu).isDisplayed()){
            takeScreenshot(driver);
            return true;
        }else{
            takeScreenshot(driver);
            return false;
        }
    }

    @Step("Check x button")
    public boolean checkXButton(){
        if(driver.findElement(Elements.menu).isDisplayed()) {
            driver.findElement(Elements.xButton).click();
        }

        if(driver.findElement(Elements.menu).isDisplayed() == false){
            takeScreenshot(driver);
            return true;
        }else{
            takeScreenshot(driver);
            return false;
        }
    }

    @Step("Check no. of cart items")
    public boolean checkNoOfItems(int noOfItems){
        int count = Integer.parseInt(driver.findElement(Elements.noOfCartItems).getText());

        if(count == noOfItems){
            return true;
        }else{
            return false;
        }
    }
/*
    @Step("Get add to cart button")
    public WebElement getInventoryItemButton(int index){
        return driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div[2]/div" +"/div" + "[" + index + "]" + "/div[3]/button"));
    }

    @Step("Get title of item")
    public WebElement getTitleOfInventoryItem(int index){
        return driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div/div[2]/div" +"/div" + "[" + index + "]" + "/div[2]/a/div"));
    }

    @Step("Add to cart")
    public void addToCart(int index) throws InterruptedException {
        WebElement button;
        String title;
        listOfItemsNames = new ArrayList<>();

        for(int i = 1; i < index; i++) {
            button = this.getInventoryItemButton(i);
            button.click();

            if (button.getText().equals("REMOVE")) {
                title = this.getTitleOfInventoryItem(i).getText();
                this.listOfItemsNames.add(title);
            }
        }
    }
*/

    @Step("Get Labels of items")
    public void getLabels(){
       List<WebElement> elements = driver.findElements(Elements.inventoryItem);
       listOfItemsNames = new ArrayList<>();
       for(int i = 0; i < elements.size(); i++){
           elements.get(i).findElement(Elements.inventoryName);
           listOfItemsNames.add(elements.get(i).findElement(Elements.inventoryName).getText());
       }
    }

    @Step("Get add to cart buttons")
    public List<WebElement> getButtons(){
        List<WebElement> webElements = driver.findElements(Elements.priceBar);
        List<WebElement> buttons = new ArrayList<>();
        for(int i = 0; i < webElements.size(); i++){

            buttons.add(webElements.get(i).findElement(By.tagName("button")));
        }

        for(int i = 0; i < buttons.size(); i++){
            buttons.get(i).click();
        }

        return buttons;

    }
    @Step("Get options names")
    public List<String> getListOfOptions(){
        List<String> listOfOptions = new ArrayList<String>();

        //Get the container
        WebElement element = driver.findElement(Elements.sortContainer);

        Select se = new Select(element);

        //Get the options of the container and add the text to a list
        for(WebElement el : se.getOptions()){
            listOfOptions.add(el.getText());
        }

        return listOfOptions;
    }

    @Step("Get items names")
    public List<String> getItemsNames(){
        List<String> listOfItems = new ArrayList<String>();

        List<WebElement> elements = driver.findElements(Elements.items);

        for(WebElement elem : elements){
            listOfItems.add(elem.getText());
        }

        return listOfItems;
    }

    @Step("Select option by index")
    public void selectOption(int index){
        Select option = new Select(driver.findElement(Elements.sortContainer));
        option.selectByIndex(index);
    }

    @Step("Get prices")
    public List<Float> getPrices(){
        List<Float> listOfPrices = new ArrayList<Float>();

        List<WebElement> elements = driver.findElements(Elements.price);

        //Get the value of prices
        for(WebElement elem : elements){
            //I use substring(1) to avoid the $ sign
            listOfPrices.add(Float.parseFloat(elem.getText().substring(1)));
        }

        return listOfPrices;
    }


    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] takeScreenshot(RemoteWebDriver driver){
        return driver.getScreenshotAs(OutputType.BYTES);
    }
}
