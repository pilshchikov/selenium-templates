package com.templates.providers;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Stepan Pilshchikov
 */
@Getter
@RequiredArgsConstructor
public enum Devices {
    NEXUS_5X(DeviceDataProvider.nexus5);

    private final DeviceData data;
}
