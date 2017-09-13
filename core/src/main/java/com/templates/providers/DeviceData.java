package com.templates.providers;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashMap;

/**
 * @author Stepan Pilshchikov
 * <p>
 * Информация по девайсу
 */
@Accessors(fluent = true)
@Builder
@Getter
@Setter
public class DeviceData {

    /**
     * Версия операционной системы
     **/
    private String OSVersion;

    /**
     * Название платформы
     **/
    private String platformName;

    /**
     * Название девайса
     **/
    private String deviceName;

    /**
     * Дополнительные опции
     **/
    private HashMap<String, Object> options;

    /**
     * Путь к приложению
     **/
    private String appPath;
}
