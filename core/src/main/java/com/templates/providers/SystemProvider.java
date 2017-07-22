package com.templates.providers;

import com.templates.enums.DriverType;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class SystemProvider {

    private static final DriverType localDriver = DriverType.CHROME;
    private static final String hubUrl = "http://localhost:4444/wd/hub";

    public static DriverType getDriverType() {
        return Arrays.stream(DriverType.values())
                .filter(driverType -> System.getProperty("driver.type").equals(driverType.name()))
                .findFirst()
                .orElse(localDriver);
    }

    public static URL getHubURL() {
        try {
            return new URL(hubUrl);
        } catch (MalformedURLException e) {
            throw new Error("Cant format hub url: " + hubUrl);
        }
    }
}
