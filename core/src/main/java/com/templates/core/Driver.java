package com.templates.core;

import com.templates.enums.DriverType;
import com.templates.providers.SystemProvider;
import io.github.bonigarcia.wdm.DriverManagerType;
import io.github.bonigarcia.wdm.WebDriverManager;
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

    private RemoteWebDriver getWebDriver() {
        switch (driverType) {
            case CHROME:
                WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
                return new ChromeDriver();
            case REMOTE_CHROME:
                return getRemoteWebDriver(DriverType.CHROME);
            default:
                throw new Error("Cant find driver type " + driverType);
        }
    }

    private ChromeOptions chromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--disable-gpu");
        return chromeOptions;
    }

    private RemoteWebDriver getRemoteWebDriver(DriverType driverType) {
        DesiredCapabilities capabilities = null;
        switch (driverType) {
            case CHROME:
                capabilities = DesiredCapabilities.chrome();
                capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions());
                capabilities.setPlatform(Platform.LINUX);
        }

        return new RemoteWebDriver(SystemProvider.getHubURL(), capabilities);
    }

    public void close() {
        if (webDriver() != null) webDriver().quit();
    }
}
