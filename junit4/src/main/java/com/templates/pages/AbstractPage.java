package com.templates.pages;

import com.templates.core.Driver;
import com.templates.annotations.Url;
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

    @Step("Кликаем на элемент: \"{element}\"")
    protected <T extends WebElement> void clickOn(T element) {

        log.info("Кликаем на элемент: \"" + element + "\"");
        try {
            element.click();
        } catch (Exception e) {
            throw new Error("Не удалось нажать на " + element + " Message: " + e.getMessage());
        }
        makeScreenShot("Кликаем на элемент: \"" + element + "\"");
    }

    @Step("Вводим текст \"{text}\" в {element}")
    protected <T extends WebElement> void inputText(T element, String text) {
        log.info("Вводим текст: " + text + " в поле: " + element);
        element.sendKeys(text);
        makeScreenShot("Вводим текст: " + text + " в элемент: " + element);
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
    }
}
