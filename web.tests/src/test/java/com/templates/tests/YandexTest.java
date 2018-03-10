package com.templates.tests;

import com.templates.steps.YandexSteps;
import com.templates.testcases.WebTestCase;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

@Feature("Search")
@Story("yandex.ru")
public class YandexTest extends WebTestCase {

    private final YandexSteps steps = new YandexSteps(driver);

    @Test
    @DisplayName("Simple search test")
    @Description("Simply open yandex.ru, type search phrase and see result items")
    public void simpleSearch() {
        steps.openYandex();
        steps.enterSearchPhrase("google chrome");
        steps.search();
        steps.resultsIsPresent();
    }
}
