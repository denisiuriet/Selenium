package resources;

import org.openqa.selenium.By;

public class Elements {


    //Login Page Elements
    public final static By username = By.id("user-name");
    public final static By password = By.id("password");
    public final static By loginButton = By.id("login-button");
    public final static By errorMessage = By.cssSelector(".login-box > form:nth-child(1) > h3:nth-child(4)");
    public static String invalidLoginError = "Epic sadface: Username and password do not match any user in this service";
    public static String userError = "Epic sadface: Username is required";
    public static String passError = "Epic sadface: Password is required";


    //Inventory Page Elements

    public final static By cart = By.cssSelector("#shopping_cart_container > a > svg");
    public final static By menuButton = By.className("bm-burger-button");
    public final static By xButton = By.cssSelector("#menu_button_container > div > div.bm-menu-wrap > div:nth-child(2) > div > button");
    public final static By menu = By.className("bm-item-list");
    public final static By sortContainer = By.className("product_sort_container");
    public final static By addToCartButton = By.cssSelector("#inventory_container > div > div:nth-child(1) > div.pricebar > button");
    public final static By noOfCartItems = By.xpath("//*[@id=\"shopping_cart_container\"]/a/span");
    public final static By items = By.className("inventory_item_name");
    public final static By price = By.className("inventory_item_price");

    public final static By inventoryItem = By.className("inventory_item");
    public final static By inventoryName = By.className("inventory_item_name");
    public final static By priceBar = By.className("pricebar");

    //Cart Page Elements

    public final static By continueShopping = By.className("btn_secondary");
    public final static By checkCheckout = By.cssSelector("#cart_contents_container > div > div.cart_footer > a.btn_action.checkout_button");
    public final static By cartItemName = By.className("inventory_item_name");

    //Checkout Page Element

    public final static By firstName = By.id("first-name");
    public final static By lastName = By.id("last-name");
    public final static By postalCode = By.id("postal-code");
    public final static By cancelButton = By.cssSelector("#checkout_info_container > div > form > div.checkout_buttons > a");
    public final static By continueButton = By.cssSelector("#checkout_info_container > div > form > div.checkout_buttons > input");
    public final static By errorMessageCheckout = By.cssSelector("#checkout_info_container > div > form > h3");

    public static String firstNameError = "Error: First Name is required";
    public static String lastNameError = "Error: Last Name is required";
    public static String postalCodeError = "Error: Postal Code is required";
}
