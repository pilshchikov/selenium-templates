package com.templates.pages;

import com.templates.annotations.Url;
import com.templates.core.Driver;
import com.templates.pages.PageObject;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import java.util.Arrays;

@Slf4j
public class AbstractPage extends PageObject {

    public AbstractPage(Driver driver) {
        super(driver, 10);
    }

    @Step("Click on : \"{element}\"")
    protected <T extends WebElement> void clickOn(T element) {

        log.info("Click on: \"" + element + "\"");
        try {
            element.click();
        } catch (Exception e) {
            throw new Error("Can't click on " + element + " Message: " + e.getMessage());
        }
        makeScreenShot("Click on: \"" + element + "\"");
    }

    @Step("Type \"{text}\" in {element}")
    protected <T extends WebElement> void inputText(T element, String text) {
        log.info("Type text: " + text + " in field: " + element);
        element.sendKeys(text);
        makeScreenShot("Type text: " + text + " in field: " + element);
    }

    @Step("Enter action sequence \"{seq}\" in {element}")
    protected <T extends WebElement> void inputSeq(T element, CharSequence seq) {
        element.sendKeys(seq);
    }

    @Attachment(value = "{name}", type = "image/png")
    public byte[] makeScreenShot(String name) {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    protected <T extends WebElement> void clearText(T element) {
        element.clear();
    }

    @Step("Open url {path}")
    protected void openPage(String path) {
        getDriver().get(path);
    }

    public void open() {
        openPage(((Url) Arrays.stream(this.getClass().getAnnotations())
                .filter(annotation -> annotation.annotationType().equals(Url.class))
                .findFirst()
                .orElseThrow(() -> new Error("Page home url is not founded")))
                .value());
        getDriver().manage().window().maximize();
    }
}
