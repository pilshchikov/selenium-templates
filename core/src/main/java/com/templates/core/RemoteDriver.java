package com.templates.core;

import com.templates.enums.DriverType;
import com.templates.providers.DeviceData;
import com.templates.providers.SystemProvider;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class RemoteDriver {

    public RemoteWebDriver getWebDriver(DriverType driverType) {

        return new RemoteWebDriver(SystemProvider.getHubURL(), getCapabilitiesForDriverType(driverType));
    }

    private Capabilities getCapabilitiesForDriverType(DriverType driverType) {

        if (driverType.equals(DriverType.ANDROID))
            return getMobileCapabilities(driverType);
        DesiredCapabilities desiredCapabilities;
        switch (driverType) {
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox");
                desiredCapabilities = DesiredCapabilities.chrome();
                desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
                desiredCapabilities.setPlatform(Platform.LINUX);
                return desiredCapabilities;
            default:
                throw new Error("Can't find driver" + driverType);
        }
    }

    private Capabilities getMobileCapabilities(DriverType driverType) {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        DeviceData deviceData = SystemProvider.getDevice(driverType).getData();
        desiredCapabilities.setCapability("version", deviceData.OSVersion());
        desiredCapabilities.setCapability("platformName", deviceData.platformName());
        desiredCapabilities.setCapability("deviceName", deviceData.deviceName());
        String appPath = deviceData.appPath();
        desiredCapabilities.setCapability("app", appPath);
        if (!deviceData.options().isEmpty())
            deviceData.options().forEach(desiredCapabilities::setCapability);
        return desiredCapabilities;
    }

    public AndroidDriver getAndroidDriver() {
        return new AndroidDriver(SystemProvider.getHubURL(), getCapabilitiesForDriverType(DriverType.ANDROID));
    }
}
