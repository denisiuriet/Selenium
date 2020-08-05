package com.sauce;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DesiredCapabilitiesSetup {
    private DesiredCapabilities capabilities;
    private ChromeOptions chromeOptions;

    public DesiredCapabilitiesSetup(){
        capabilities = new DesiredCapabilities();
        chromeOptions = new ChromeOptions();
    }

    public DesiredCapabilities setup(){
        this.capabilities.setCapability("browserName", "chrome");
        this.capabilities.setCapability("Platform", "ANY");
        this.capabilities.setCapability("javascriptEnable", "true");
        chromeOptions.addArguments("headless", "true");
        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        System.getProperty("resourcePath", System.getProperty("user.dir") + "/src/main/resources/");

        return capabilities;
    }
}
