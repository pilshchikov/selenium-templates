package com.templates.providers;

import java.util.HashMap;

/**
 * @author Stepan Pilshchikov
 */
public class DeviceDataProvider {

    private static final String androidFilePath = "/storage/androidApp/app.apk";

    public static final DeviceData nexus5 = DeviceData.builder()
            .OSVersion("7.0.0")
            .platformName("ANDROID")
            .appPath(androidFilePath)
            .options(androidOptions())
            .deviceName("Nexus_5X_API_24")
            .build();

    private static HashMap<String, Object> androidOptions() {
        HashMap<String, Object> options = new HashMap<>();
        options.put("clearSystemFiles", true);
        return options;
    }
}
