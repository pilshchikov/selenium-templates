package templates.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import templates.steps.DuckDuckGoSteps;
import templates.testcases.WebTestCase;

@Feature("Search")
@Story("duckduckgo.com")
public class DuckDuckGoTest extends WebTestCase {

    private final DuckDuckGoSteps steps = new DuckDuckGoSteps(driver);

    @Test(testName = "Simple search test",
            description = "Simply open duckduckgo.com, type search phrase and see result items")
    public void simpleSearch() throws Exception {
        steps.openDuckDuckGo();
        steps.enterSearchPhrase("duckduckgo have convenient layouts");
        steps.search();
        steps.resultsIsPresent();
    }
}
