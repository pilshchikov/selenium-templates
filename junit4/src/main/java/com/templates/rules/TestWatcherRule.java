package com.templates.rules;

import com.templates.core.Driver;
import io.qameta.allure.Attachment;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

/**
 * @author Stepan Pilshchikov
 * @created 23.07.2017
 */
public class TestWatcherRule extends TestWatcher {

    private final Driver driver;

    public TestWatcherRule(Driver driver) {
        this.driver = driver;
    }

    @Override
    protected void failed(Throwable e, Description description) {
        makeFailScreenShot();
    }

    @Override
    protected void finished(Description description) {
        driver.webDriver().close();
    }

    @Attachment("Test fail screenshot")
    public byte[] makeFailScreenShot() {
        return ((TakesScreenshot) driver.webDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
