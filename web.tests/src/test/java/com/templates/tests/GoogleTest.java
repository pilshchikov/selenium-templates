package com.templates.tests;

import com.templates.steps.GoogleSteps;
import com.templates.testcases.WebTestCase;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

@Feature("Search")
@Story("google.com")
public class GoogleTest extends WebTestCase {

    private final GoogleSteps steps = new GoogleSteps(driver);

    @Test
    @DisplayName("Simple search test")
    @Description("Simply open google.com, type search phrase and see result items")
    public void simpleSearch() {
        steps.openGoogle();
        steps.enterSearchPhrase("yandex browser");
        steps.search();
        steps.resultsIsPresent();
    }
}
