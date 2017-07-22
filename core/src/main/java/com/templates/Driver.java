package com.templates;

import com.templates.enums.DriverType;
import com.templates.providers.SystemProvider;
import lombok.Getter;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Driver {

    @Getter(lazy = true)
    private final RemoteWebDriver webDriver = getDriver(SystemProvider.getDriverType());

    private RemoteWebDriver getDriver(DriverType driverType) {
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

    public void close() {
        if (webDriver != null) webDriver.quit();
    }
}
