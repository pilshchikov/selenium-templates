package com.templates.tests;


import com.templates.steps.GoogleSteps;
import com.templates.testcases.WebTestCase;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

@Feature("Search")
@Story("google.com")
@Test(groups = {"all", "google"})
public class GoogleTest extends WebTestCase {

    private final GoogleSteps steps = new GoogleSteps(driver);

    @Test(description = "Simple search test")
    @Description("Simply open google.com, type search phrase and see result items")
    public void simpleSearch() throws Exception {
        steps.openGoogle();
        steps.enterSearchPhrase("yandex browser");
        steps.search();
        steps.resultsIsPresent();
    }
}
