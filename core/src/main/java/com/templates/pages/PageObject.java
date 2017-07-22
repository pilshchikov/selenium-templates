package com.templates.pages;

import com.templates.core.Driver;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class PageObject {

    protected RemoteWebDriver webDriver;
    private Integer implicitWait;

    public PageObject(Driver driver, Integer implicitWait) {
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver.webDriver())), this);
        this.webDriver = driver.webDriver();
        this.implicitWait = implicitWait;
    }

    protected RemoteWebDriver getDriver() {
        return webDriver;
    }

    public Boolean waitForPresent(WebElement element, Integer... waitInSec) {
        return waitFor(ExpectedConditions.visibilityOf(element), waitInSec);
    }

    public Boolean waitForNotPresent(WebElement element, Integer... waitInSec) {
        return waitFor(ExpectedConditions.invisibilityOf(element), waitInSec);
    }

    public <T extends WebElement> Boolean waitForListPresent(List<T> elementList, Integer... waitInSec) {
        return waitFor(ExpectedConditions.visibilityOfAllElements((List<WebElement>) elementList), waitInSec);
    }

    private Boolean waitFor(ExpectedCondition condition, Integer... waitInSec) {
        return waitBase(condition, waitInSec.length > 0 ? waitInSec[0] : implicitWait);
    }

    private Boolean waitBase(ExpectedCondition condition, Integer waitInSec) {
        FluentWait wait = new FluentWait<WebDriver>(webDriver)
                .pollingEvery(1, TimeUnit.SECONDS)
                .withTimeout(waitInSec, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
        try {
            wait.until(condition);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
