package com.templates.steps;

import com.templates.core.Driver;
import com.templates.pages.AbstractPage;
import lombok.Getter;
import lombok.experimental.Accessors;

public class CommonSteps {

    private Driver driver;

    @Accessors(fluent = true)
    @Getter(lazy = true)
    private final AbstractPage page = new AbstractPage(driver);

    public CommonSteps(Driver driver) {
        this.driver = driver;
    }
}
