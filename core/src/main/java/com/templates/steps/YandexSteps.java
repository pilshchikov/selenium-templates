package com.templates.steps;

import com.templates.core.Driver;
import com.templates.pages.yandex.YandexPage;

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

    public void enterSearchPhrase(String phrase) {
        yandexPage.typeSearchPhrase(phrase);
    }

    public void search() {
        yandexPage.search();
    }

    public void resultsIsPresent() {
        assertTrue("Results is not loaded", page().waitForListPresent(yandexPage.resultItems));
    }
}
