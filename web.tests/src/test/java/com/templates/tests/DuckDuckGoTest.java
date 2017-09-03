package com.templates.tests;

import com.templates.steps.DuckDuckGoSteps;
import com.templates.testcases.WebTestCase;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

@Feature("Search")
@Story("duckduckgo.com")
public class DuckDuckGoTest extends WebTestCase {

    private final DuckDuckGoSteps steps = new DuckDuckGoSteps(driver);

    @Test
    @DisplayName("Simple search test")
    @Description("Simply open duckduckgo.com, type search phrase and see result items")
    public void simpleSearch() throws Exception {
        steps.openDuckDuckGo();
        steps.enterSearchPhrase("duckduckgo have convenient layouts");
        steps.search();
        steps.resultsIsPresent();
    }
}
