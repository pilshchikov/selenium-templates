package com.templates.core;

import com.templates.enums.DriverType;
import com.templates.providers.SystemProvider;
import io.appium.java_client.AppiumDriver;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class Driver {

    private DriverType driverType = SystemProvider.getDriverType();

    @Accessors(fluent = true)
    @Getter(lazy = true)
    private final RemoteWebDriver webDriver = getWebDriver();

    @Accessors(fluent = true)
    @Getter(lazy = true)
    private final AppiumDriver appiumDriver = getAndroidDriver();

    private RemoteWebDriver getWebDriver() {
        switch (driverType) {
            case CHROME:
                return new ChromeDriver(DesiredCapabilities.chrome());
            case REMOTE_CHROME:
                return getRemoteWebDriver(DriverType.CHROME);
            default:
                throw new Error("Cant find driver type" + driverType);
        }
    }

    private RemoteWebDriver getRemoteWebDriver(DriverType driverType) {
        DesiredCapabilities capabilities = null;
        switch (driverType) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox");
                capabilities = DesiredCapabilities.chrome();
                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                capabilities.setPlatform(Platform.LINUX);
        }

        return new RemoteWebDriver(SystemProvider.getHubURL(), capabilities);
    }

    private AppiumDriver getAndroidDriver() {
        return new RemoteDriver().getAndroidDriver();
    }

    public void close() {
        if (webDriver() != null) webDriver().quit();
        if (appiumDriver() != null) appiumDriver().quit();
    }
}
