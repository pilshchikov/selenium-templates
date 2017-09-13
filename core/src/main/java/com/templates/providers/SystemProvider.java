package com.templates.providers;

import com.templates.enums.DriverType;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class SystemProvider {

    private static final DriverType LOCAL_DRIVER = DriverType.CHROME;
    private static final String HUB_URL = "http://localhost:4444/wd/hub";

    /**
     * Девайс, если запускаем локально
     */
    private static final Devices LOCAL_DEVICE = Devices.NEXUS_5X;

    public static DriverType getDriverType() {
        return Arrays.stream(DriverType.values())
                .filter(driverType -> System.getProperty("driver.type", "null").equals(driverType.name()))
                .findFirst()
                .orElse(LOCAL_DRIVER);
    }

    public static URL getHubURL() {
        try {
            return new URL(HUB_URL);
        } catch (MalformedURLException e) {
            throw new Error("Cant format hub url: " + HUB_URL);
        }
    }

    public static Devices getDevice(DriverType driverType) {
        String deviceName = System.getProperty("mobile.device", LOCAL_DEVICE.name());
        return Arrays.stream(Devices.values())
                .filter(item -> item.name().equals(deviceName))
                .findFirst().orElseThrow(() -> new Error("Не нашли девайс " + driverType));
    }
}
