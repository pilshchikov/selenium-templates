package com.templates.steps;

import com.templates.core.Driver;
import com.templates.pages.yandex.YandexPage;
import io.qameta.allure.Step;

import static org.junit.Assert.assertTrue;

/**
 * @author Stepan Pilshchikov
 * @created 22.07.2017
 */
public class YandexSteps extends CommonSteps {

    private YandexPage yandexPage;

    public YandexSteps(Driver driver) {
        super(driver);
        yandexPage = new YandexPage(driver);
    }

    public void openYandex() {
        yandexPage.open();
        assertTrue(page().waitForPresent(yandexPage.arrow));
    }

    @Step("Enter search phrase: {phrase}")
    public void enterSearchPhrase(String phrase) {
        yandexPage.typeSearchPhrase(phrase);
    }

    @Step("Click search button")
    public void search() {
        yandexPage.search();
    }

    @Step("Results is present")
    public void resultsIsPresent() {
        assertTrue("Results is not loaded", page().waitForListPresent(yandexPage.resultItems));
        page().makeScreenShot("Results");
    }
}
