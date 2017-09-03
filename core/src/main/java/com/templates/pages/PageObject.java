package com.templates.pages;

import com.templates.core.Driver;
import com.templates.utils.CommonFunctions;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.util.List;
import java.util.concurrent.Callable;

import static java.util.concurrent.TimeUnit.SECONDS;

public class PageObject {

    @Accessors(fluent = true)
    @Getter(lazy = true)
    private final CommonFunctions functions = new CommonFunctions();

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
        return waitFor(element::isDisplayed, false, waitInSec);
    }

    public Boolean waitForNotPresent(WebElement element, Integer... waitInSec) {
        return waitFor(() -> !element.isDisplayed(), true, waitInSec);
    }

    public <T extends WebElement> Boolean waitForListPresent(List<T> elementList, Integer... waitInSec) {
        return waitFor(() -> elementList.size() > 0, false, waitInSec);
    }

    private Boolean waitFor(Callable<Boolean> condition, Boolean exceptionConditionState, Integer... waitInSeconds) {
        getDriver().manage().timeouts().implicitlyWait(0, SECONDS);
        Boolean result = functions().waitFor(
                () -> conditionExecute(condition, exceptionConditionState),
                waitInSeconds.length > 0 ? waitInSeconds[0] : implicitWait, 1);
        getDriver().manage().timeouts().implicitlyWait(implicitWait, SECONDS);
        return result;
    }

    private Boolean conditionExecute(Callable<Boolean> condition, Boolean exceptionConditionState) {
        try {
            return condition.call();
        } catch (StaleElementReferenceException
                | NoSuchElementException
                | ElementNotVisibleException
                | NullPointerException e) {
            return exceptionConditionState;
        } catch (Exception e) {
            throw new Error("Condition throw Exception: " + e);
        }
    }
}
