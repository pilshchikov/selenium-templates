package com.templates.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import com.templates.steps.DuckDuckGoSteps;
import com.templates.testcases.WebTestCase;

@Feature("Search")
@Story("duckduckgo.com")
@Test(groups = {"all", "duckduckgo"})
public class DuckDuckGoTest extends WebTestCase {

    private final DuckDuckGoSteps steps = new DuckDuckGoSteps(driver);

    @Test(description = "Simple search test")
    @Description("Simply open duckduckgo.com, type search phrase and see result items")
    public void simpleSearch() throws Exception {
        steps.openDuckDuckGo();
        steps.enterSearchPhrase("duckduckgo have convenient layouts");
        steps.search();
        steps.resultsIsPresent();
    }
}
