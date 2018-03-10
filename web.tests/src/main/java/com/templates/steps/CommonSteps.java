package com.templates.steps;

import com.templates.core.Driver;
import com.templates.enums.AttachmentFormat;
import com.templates.pages.AbstractPage;
import com.templates.utils.CommonFunctions;
import io.qameta.allure.Step;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.junit.Assert;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import static org.junit.Assert.*;

public class CommonSteps {

    protected Driver driver;

    @Accessors(fluent = true)
    @Getter(lazy = true)
    private final CommonFunctions functions = new CommonFunctions();

    @Accessors(fluent = true)
    @Getter(lazy = true)
    private final AbstractPage page = new AbstractPage(driver);

    public CommonSteps(Driver driver) {
        this.driver = driver;
    }

    protected void present(HtmlElement element) {
        assertTrue(element.getName() + " is not present", page().waitForPresent(element));
    }

    protected void notPresent(HtmlElement element) {
        assertTrue(element.getName() + " is present", page().waitForNotPresent(element));
    }
}
