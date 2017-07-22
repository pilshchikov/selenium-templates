package com.templates.pages;

import com.templates.Driver;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;

@Slf4j
public class AbstractPage extends PageObject {
    public AbstractPage(Driver driver) {
        super(driver, 10);
    }

    public <T extends WebElement> void clickOn(T element) {

        log.info("Кликаем на элемент: \"" + element + "\"");
        try {
            element.click();
        } catch (Exception e) {
            throw new Error("Не удалось нажать на " + element + " Message: " + e.getMessage());
        }
        makeScreenShot("Кликаем на элемент: \"" + element + "\"");
    }

    public <T extends WebElement> void inputText(T element, String text) {

        log.info("Вводим текст: " + text + " в поле: " + element);
        element.sendKeys(text);
        makeScreenShot("Вводим текст: " + text + " в элемент: " + element);
    }
}
