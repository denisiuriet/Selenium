package com.sauce;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class SingletonDriver {

    private static RemoteWebDriver instance;

    static{
        try {
            DesiredCapabilitiesSetup desiredCapabilities = new DesiredCapabilitiesSetup();
            instance = new RemoteWebDriver(new URL("http://localhost:5555/wd/hub"), desiredCapabilities.setup());

        }catch(MalformedURLException e){
            e.printStackTrace();
            System.out.println("The driver couldn't be created !");
        }
    }

    public static RemoteWebDriver getInstance(){
        return instance;
    }


}
